package com.nextplugins.commandpassword.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("messages.yml")
@ConfigSection("messages")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageValue implements ConfigurationInjectable {

    @Getter private static final MessageValue instance = new MessageValue();

    // login

    @ConfigField("login.not-logged-in") private String notLogged;
    @ConfigField("login.successfully-logged-in") private String successfullyLogged;
    @ConfigField("login.wrong-password") private String wrongPassword;
    @ConfigField("login.already-logged-in") private String alreadyLogged;
    @ConfigField("login.type-the-password") private String typeThePassword;

    public static <T> T get(Function<MessageValue, T> function) {
        return function.apply(instance);
    }

}
