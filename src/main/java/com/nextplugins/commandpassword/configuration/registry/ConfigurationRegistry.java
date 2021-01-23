package com.nextplugins.commandpassword.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.commandpassword.NextCommandPassword;

public final class ConfigurationRegistry {

    public void init() {
        NextCommandPassword plugin = NextCommandPassword.getInstance();

        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);
    }

}
