package com.nextplugins.commandpassword.listener.registry;

import com.nextplugins.commandpassword.NextCommandPassword;
import com.nextplugins.commandpassword.listener.CommandExecuteListener;
import com.nextplugins.commandpassword.listener.PlayerJoinListener;
import com.nextplugins.commandpassword.listener.PlayerQuitListener;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public final class ListenerRegistry {

    private final NextCommandPassword nextCommandPassword;

    public void register() {
        try {
            Bukkit.getPluginManager().registerEvents(
                    new PlayerJoinListener(
                            nextCommandPassword.getCommandUserManager(),
                            nextCommandPassword.getLockedCommandManager()
                    ),
                    nextCommandPassword
            );
            Bukkit.getPluginManager().registerEvents(
                    new PlayerQuitListener(nextCommandPassword.getCommandUserManager()),
                    nextCommandPassword
            );
            Bukkit.getPluginManager().registerEvents(
                    new CommandExecuteListener(
                            nextCommandPassword.getCommandUserManager(),
                            nextCommandPassword.getLockedCommandManager()
                    ),
                    nextCommandPassword
            );

            nextCommandPassword.getLogger().info("Listeners registrados com sucesso.");
        } catch (Throwable t) {
            t.printStackTrace();
            nextCommandPassword.getLogger().severe("Não foi possível registrar os listeners!");
        }
    }

}
