package com.nextplugins.commandpassword.util.event;

import com.nextplugins.commandpassword.NextCommandPassword;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;

public final class ChatAsk {

    private final static WeakHashMap<Player, ChatAsk> asks = new WeakHashMap<>();

    static {
        NextCommandPassword plugin = NextCommandPassword.getInstance();
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onChat(AsyncPlayerChatEvent event) {
                ChatAsk ask = asks.get(event.getPlayer());

                if (ask != null) {
                    event.setCancelled(true);

                    final BiConsumer<Player, String> consumer = ask.getOnComplete();

                    final String message = event.getMessage();
                    final Player player = event.getPlayer();

                    asks.remove(player);

                    consumer.accept(player, message);
                }
            }

            @EventHandler
            public void onQuit(PlayerQuitEvent event) {
                asks.remove(event.getPlayer());
            }
        }, plugin);
    }

    private final String[] messages;
    private final BiConsumer<Player, String> onComplete;

    private ChatAsk(Builder builder) {
        messages = builder.messages;
        onComplete = builder.onComplete;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addPlayer(Player player) {
        asks.put(player, this);
        player.sendMessage(messages);
    }

    public BiConsumer<Player, String> getOnComplete() {
        return onComplete;
    }

    public String[] getMessages() {
        return messages;
    }

    public static final class Builder {

        private String[] messages;
        private BiConsumer<Player, String> onComplete;

        private Builder() {
        }

        public Builder messages(String... messages) {
            this.messages = messages;
            return this;
        }

        public Builder messages(List<String> messages) {
            this.messages = messages.toArray(new String[0]);
            return this;
        }

        public Builder onComplete(BiConsumer<Player, String> onComplete) {
            this.onComplete = onComplete;
            return this;
        }

        public ChatAsk build() {
            return new ChatAsk(this);
        }

    }

}
