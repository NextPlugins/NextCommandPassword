package com.nextplugins.commandpassword.manager;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.model.LockedCommand;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedList;
import java.util.List;

public final class LockedCommandManager {

    @Getter private final List<LockedCommand> lockedCommandList = new LinkedList<>();

    public void init() {

        ConfigurationSection section = ConfigurationValue.get(ConfigurationValue::lockedCommandsSection);

        for (String command : section.getKeys(false)) {

            LockedCommand lockedCommand = LockedCommand.builder()
                    .id(command)
                    .command(section.getString(command + ".command"))
                    .password(section.getString(command + ".password"))
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
                .filter(lockedCommand -> lockedCommand.getCommand().equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

}
