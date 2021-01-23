package com.nextplugins.commandpassword;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextCommandPassword extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
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
