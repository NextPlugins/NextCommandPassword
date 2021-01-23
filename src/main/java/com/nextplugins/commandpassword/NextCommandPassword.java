package com.nextplugins.commandpassword;

import com.nextplugins.commandpassword.listener.registry.ListenerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextCommandPassword extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            ListenerRegistry.of(this).register();

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
