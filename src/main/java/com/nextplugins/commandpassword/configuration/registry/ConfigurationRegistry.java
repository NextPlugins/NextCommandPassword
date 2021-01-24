package com.nextplugins.commandpassword.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.commandpassword.NextCommandPassword;
import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import com.nextplugins.commandpassword.configuration.MessageValue;

public final class ConfigurationRegistry {

    public void init() {
        NextCommandPassword plugin = NextCommandPassword.getInstance();

        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "messages.yml"
        );

        configurationInjector.injectConfiguration(
                ConfigurationValue.instance(),
                MessageValue.instance()
        );
    }

}
