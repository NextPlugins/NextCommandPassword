package com.nextplugins.commandpassword.manager;

import com.nextplugins.commandpassword.model.user.CommandUser;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public final class CommandUserManager {

    @Getter private final List<CommandUser> commandUserList = new LinkedList<>();

    public void addUser(CommandUser commandUser) {
        if (!commandUserList.contains(commandUser)) {
            commandUserList.add(commandUser);
        }
    }

    public void removeUser(CommandUser commandUser) {
        commandUserList.remove(commandUser);
    }

    public CommandUser findUserByPlayer(Player player) {
        return commandUserList.stream()
                .filter(commandUser -> commandUser.getUser().equals(player.getName()))
                .findFirst()
                .orElse(null);
    }

}
