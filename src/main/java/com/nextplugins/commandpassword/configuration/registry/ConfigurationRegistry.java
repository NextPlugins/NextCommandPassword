package com.nextplugins.commandpassword.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.commandpassword.NextCommandPassword;
import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.configuration.MessageValue;
import lombok.val;

public final class ConfigurationRegistry {

    public void init() {
        val plugin = NextCommandPassword.getInstance();

        val configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "messages.yml"
        );

        configurationInjector.injectConfiguration(
                ConfigurationValue.instance(),
                MessageValue.instance()
        );
    }

}
