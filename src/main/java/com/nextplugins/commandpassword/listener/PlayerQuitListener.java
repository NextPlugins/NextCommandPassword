package com.nextplugins.commandpassword.listener;

import com.nextplugins.commandpassword.configuration.ConfigurationValue;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        String permission = ConfigurationValue.get(ConfigurationValue::permission);

        if (player.hasPermission(permission)) {
            System.out.println("Testando - " + permission);
        }
    }

}
