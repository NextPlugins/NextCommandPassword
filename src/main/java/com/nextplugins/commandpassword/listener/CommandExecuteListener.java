package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.NextCommandPassword;
import com.nextplugins.commandpassword.configuration.MessageValue;
import com.nextplugins.commandpassword.util.event.ChatAsk;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public final class CommandExecuteListener implements Listener {

    private final NextCommandPassword plugin;

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent event) {
        val lockedCommandManager = plugin.getLockedCommandManager();
        val commandUserManager = plugin.getCommandUserManager();

        val player = event.getPlayer();

        val lockedCommand = lockedCommandManager.findCommandByLabel(event.getMessage());

        if (lockedCommand != null) {
            val commandUser = commandUserManager.findUserByPlayer(player);

            if (commandUser != null) {
                if (!commandUser.getLoggedInCommands().get(lockedCommand)) {
                    player.sendMessage(MessageValue.get(MessageValue::notLogged));
                    event.setCancelled(true);

                    ChatAsk.builder()
                            .messages(MessageValue.get(MessageValue::typeThePassword))
                            .onComplete((whoAnswered, message) -> {
                                if (message.equalsIgnoreCase("cancelar")) {
                                    whoAnswered.sendMessage(MessageValue.get(MessageValue::cancelled));
                                    return;
                                }

                                lockedCommandManager.tryLogin(commandUser, lockedCommand, message);
                            })
                            .build().addPlayer(player);
                }
            }
        }
    }

}
