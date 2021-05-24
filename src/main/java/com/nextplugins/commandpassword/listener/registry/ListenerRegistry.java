package com.nextplugins.commandpassword.listener.registry;

import com.nextplugins.commandpassword.NextCommandPassword;
import com.nextplugins.commandpassword.listener.CommandExecuteListener;
import com.nextplugins.commandpassword.listener.PlayerJoinListener;
import com.nextplugins.commandpassword.listener.PlayerQuitListener;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public final class ListenerRegistry {

    private final NextCommandPassword plugin;

    public void register() {
        try {
            Bukkit.getPluginManager().registerEvents(
                    new PlayerJoinListener(
                            plugin.getCommandUserManager(),
                            plugin.getLockedCommandManager()
                    ),
                    plugin
            );
            Bukkit.getPluginManager().registerEvents(
                    new PlayerQuitListener(plugin.getCommandUserManager()),
                    plugin
            );
            Bukkit.getPluginManager().registerEvents(
                    new CommandExecuteListener(plugin),
                    plugin
            );

            plugin.getTextLogger().info("Listeners registrados com sucesso.");
        } catch (Throwable t) {
            t.printStackTrace();
            plugin.getTextLogger().error("Não foi possível registrar os listeners!");
        }
    }

}
