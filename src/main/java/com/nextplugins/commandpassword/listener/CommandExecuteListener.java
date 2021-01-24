package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.MessageValue;
import com.nextplugins.commandpassword.manager.CommandUserManager;
import com.nextplugins.commandpassword.manager.LockedCommandManager;
import com.nextplugins.commandpassword.model.LockedCommand;
import com.nextplugins.commandpassword.model.user.CommandUser;
import com.nextplugins.commandpassword.util.ChatAsker;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public final class CommandExecuteListener implements Listener {

    private final CommandUserManager commandUserManager;
    private final LockedCommandManager lockedCommandManager;

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        LockedCommand lockedCommand = lockedCommandManager.findCommandByLabel(event.getMessage());

        if (lockedCommand != null) {

            CommandUser commandUser = commandUserManager.findUserByPlayer(player);

            if (commandUser != null) {

                if (!commandUser.getLogins().get(lockedCommand)) {
                    player.sendMessage(MessageValue.get(MessageValue::notLogged));
                    event.setCancelled(true);

                    ChatAsker.builder()
                            .messages(
                                    "",
                                    MessageValue.get(MessageValue::typeThePassword),
                                    ""
                            )
                            .onComplete((whoAnswered, message) -> lockedCommandManager.login(commandUser, lockedCommand, message))
                            .build()
                            .addPlayer(player);

                }

            }

        }

    }

}
