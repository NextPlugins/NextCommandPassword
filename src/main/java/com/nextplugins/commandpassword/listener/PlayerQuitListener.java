package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public final class PlayerQuitListener implements Listener {

    private final CommandUserManager commandUserManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        val player = event.getPlayer();

        val permission = ConfigurationValue.get(ConfigurationValue::permission);

        if (player.hasPermission(permission)) {
            val commandUser = commandUserManager.findUserByPlayer(player);

            if (commandUser != null) {
                commandUserManager.removeUser(commandUser);
            }
        }
    }

}
