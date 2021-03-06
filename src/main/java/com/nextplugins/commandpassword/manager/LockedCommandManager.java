package com.nextplugins.commandpassword.manager;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.configuration.MessageValue;
import com.nextplugins.commandpassword.model.LockedCommand;
import com.nextplugins.commandpassword.model.user.CommandUser;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public final class LockedCommandManager {

    @Getter private final List<LockedCommand> lockedCommandList = new LinkedList<>();

    public void init() {

        ConfigurationSection section = ConfigurationValue.get(ConfigurationValue::lockedCommandsSection);

        for (String command : section.getKeys(false)) {

            String globalPassword = ConfigurationValue.get(ConfigurationValue::globalPassword);

            String commandLabel = section.getString(command + ".command");
            String password;

            if (section.getString(command + ".password") == null ||
                    section.getString(command + ".password").equals("null")) {
                password = globalPassword;
            } else {
                password = section.getString(command + ".password");
            }

            LockedCommand lockedCommand = LockedCommand.builder()
                    .id(command)
                    .command(commandLabel)
                    .password(password)
                    .build();

            addCommand(lockedCommand);

        }

    }

    public void addCommand(LockedCommand lockedCommand) {
        if (!lockedCommandList.contains(lockedCommand)) {
            lockedCommandList.add(lockedCommand);
        }
    }

    public void removeCommand(LockedCommand lockedCommand) {
        lockedCommandList.remove(lockedCommand);
    }

    public LockedCommand findCommandByLabel(String label) {
        return lockedCommandList.stream()
                .filter(lockedCommand -> lockedCommand.getCommand().startsWith(label))
                .findFirst()
                .orElse(null);
    }

    public void login(CommandUser commandUser, LockedCommand lockedCommand, String password) {
        Player player = Bukkit.getPlayer(commandUser.getUser());

        if (!commandUser.getLogins().get(lockedCommand)) {
            if (lockedCommand.getPassword().equals(password)) {
                commandUser.getLogins().replace(lockedCommand, true);
                player.sendMessage(MessageValue.get(MessageValue::successfullyLogged));
            } else {
                player.sendMessage(MessageValue.get(MessageValue::wrongPassword));
            }
        } else {
            player.sendMessage(MessageValue.get(MessageValue::alreadyLogged));
        }
    }

}
