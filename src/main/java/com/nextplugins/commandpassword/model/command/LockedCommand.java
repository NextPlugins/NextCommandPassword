package com.nextplugins.commandpassword.model.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class LockedCommand {

    private final String id;

    private final String command;
    private final String password;

}
