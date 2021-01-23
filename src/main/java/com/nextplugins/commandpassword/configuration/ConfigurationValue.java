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
import org.bukkit.configuration.ConfigurationSection;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("config.yml")
@ConfigSection("plugin")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationValue implements ConfigurationInjectable {

    @Getter private static final ConfigurationValue instance = new ConfigurationValue();

    // permission

    @ConfigField("permission") private String permission;

    // password

    @ConfigField("global-password") private String globalPassword;

    // commands

    @ConfigField("locked-commands") private ConfigurationSection lockedCommandsSection;

    public static <T> T get(Function<ConfigurationValue, T> function) {
        return function.apply(instance);
    }

}
