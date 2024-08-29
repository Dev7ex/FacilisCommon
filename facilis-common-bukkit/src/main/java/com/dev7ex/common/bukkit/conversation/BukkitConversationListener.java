package com.dev7ex.common.bukkit.conversation;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public class BukkitConversationListener implements Listener {

    private final BukkitConversationModule conversationModule;

    public BukkitConversationListener(@NotNull final BukkitConversationModule conversationModule) {
        this.conversationModule = conversationModule;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (this.conversationModule.getConversation(player.getUniqueId()).isEmpty()) {
            return;
        }

        final BukkitConversation conversation = this.conversationModule.getConversation(player.getUniqueId()).get();
        final BukkitConversationFlag conversationFlag = new BukkitConversationFlag();

        conversation.begin(event.getMessage(), conversationFlag);

        event.setCancelled(conversationFlag.isCancelMessage());

        if (conversationFlag.isCloseConversation()) {
            this.conversationModule.unregisterConversation(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        this.conversationModule.unregisterConversation(event.getPlayer().getUniqueId());
    }

}
