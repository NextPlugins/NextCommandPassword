package com.nextplugins.commandpassword;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.configuration.registry.ConfigurationRegistry;
import com.nextplugins.commandpassword.listener.registry.ListenerRegistry;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.manager.LockedCommandManager;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NextCommandPassword extends JavaPlugin {

    private final ConfigurationRegistry configurationRegistry = new ConfigurationRegistry();

    private final LockedCommandManager lockedCommandManager = new LockedCommandManager();
    private final CommandUserManager commandUserManager = new CommandUserManager();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try {
            ListenerRegistry.of(this).register();

            configurationRegistry.init();

            lockedCommandManager.init();

            getLogger().info("Plugin ativado com sucesso!");
        } catch (Throwable t) {
            t.printStackTrace();
            getLogger().info("Ocorreu um erro durante a inicialização do plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public static NextCommandPassword getInstance() {
        return getPlugin(NextCommandPassword.class);
    }

    private void configureBStats() {
        if (ConfigurationValue.get(ConfigurationValue::useBStats)) {
            int pluginId = 10102;
            new Metrics(this, pluginId);
            getLogger().info("Integração com o bStats configurada com sucesso.");
        } else {
            getLogger().info("Você desabilitou o uso do bStats, portanto, não utilizaremos dele.");
        }
    }

}
