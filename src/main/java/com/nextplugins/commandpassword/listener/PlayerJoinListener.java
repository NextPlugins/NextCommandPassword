package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.manager.LockedCommandManager;
import com.nextplugins.commandpassword.model.command.LockedCommand;
import com.nextplugins.commandpassword.model.user.CommandUser;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public final class PlayerJoinListener implements Listener {

    private final CommandUserManager commandUserManager;
    private final LockedCommandManager lockedCommandManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        val player = event.getPlayer();

        val permission = ConfigurationValue.get(ConfigurationValue::permission);

        if (player.hasPermission(permission)) {
            final Map<LockedCommand, Boolean> commandMap = new LinkedHashMap<>();

            for (LockedCommand lockedCommand : lockedCommandManager.getLockedCommandList()) {
                commandMap.put(lockedCommand, false);
            }

            commandUserManager.addUser(
                    CommandUser.builder()
                            .user(player.getName())
                            .loggedInCommands(commandMap)
                            .build()
            );
        }
    }

}
