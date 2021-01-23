package com.nextplugins.commandpassword.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CommandUser {

    private final String user;
    private boolean logged;

}
