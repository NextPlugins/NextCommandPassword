package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.model.user.CommandUser;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public final class PlayerQuitListener implements Listener {

    private final CommandUserManager commandUserManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String permission = ConfigurationValue.get(ConfigurationValue::permission);

        if (player.hasPermission(permission)) {

            CommandUser commandUser = commandUserManager.findUserByPlayer(player);

            if (commandUser != null) {
                commandUserManager.removeUser(commandUser);
            }

        }

    }

}
