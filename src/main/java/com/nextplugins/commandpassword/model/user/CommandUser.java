package com.nextplugins.commandpassword.model.user;

import com.nextplugins.commandpassword.model.command.LockedCommand;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public final class CommandUser {

    private final String user;
    private Map<LockedCommand, Boolean> loggedInCommands;

}
