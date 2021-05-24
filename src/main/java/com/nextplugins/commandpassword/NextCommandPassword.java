package com.nextplugins.commandpassword;

import com.google.common.base.Stopwatch;
import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.configuration.registry.ConfigurationRegistry;
import com.nextplugins.commandpassword.listener.registry.ListenerRegistry;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.manager.LockedCommandManager;
import com.nextplugins.commandpassword.util.text.TextLogger;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NextCommandPassword extends JavaPlugin {

    private final ConfigurationRegistry configurationRegistry = new ConfigurationRegistry();

    private final LockedCommandManager lockedCommandManager = new LockedCommandManager();
    private final CommandUserManager commandUserManager = new CommandUserManager();

    private final TextLogger textLogger = new TextLogger();

    private final Stopwatch initTiming = Stopwatch.createUnstarted();

    @Override
    public void onLoad() {
        initTiming.start();

        textLogger.info("Carregando o plugin...");

        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        try {
            ListenerRegistry.of(this).register();

            configurationRegistry.init();

            lockedCommandManager.init();

            configureBStats();

            initTiming.stop();

            textLogger.info(String.format("Plugin carregado com sucesso! (%s)", initTiming));
        } catch (Throwable t) {
            t.printStackTrace();
            textLogger.error("Ocorreu um erro durante a inicialização do plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        textLogger.info("Descarregando os módulos do plugin...");

        textLogger.info("Plugin descarregado com sucesso.");
    }

    public static NextCommandPassword getInstance() {
        return getPlugin(NextCommandPassword.class);
    }

    private void configureBStats() {
        if (ConfigurationValue.get(ConfigurationValue::useBStats)) {
            int pluginId = 10102;
            new Metrics(this, pluginId);
            textLogger.info("Integração com o bStats configurada com sucesso.");
        } else {
            textLogger.info("Você desabilitou o uso do bStats, portanto, não utilizaremos dele.");
        }
    }

}
