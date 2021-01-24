package com.nextplugins.commandpassword;

import com.nextplugins.commandpassword.configuration.registry.ConfigurationRegistry;
import com.nextplugins.commandpassword.listener.registry.ListenerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextCommandPassword extends JavaPlugin {

    private final ConfigurationRegistry configurationRegistry = new ConfigurationRegistry();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try {
            ListenerRegistry.of(this).register();

            configurationRegistry.init();

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

}
