package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.model.user.CommandUser;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public final class PlayerJoinListener implements Listener {

    private final CommandUserManager commandUserManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String permission = ConfigurationValue.get(ConfigurationValue::permission);

        if (player.hasPermission(permission)) {

            commandUserManager.addUser(
                    CommandUser.builder()
                            .user(player.getName())
                            .logged(false)
                            .build()
            );

        }
    }

}
