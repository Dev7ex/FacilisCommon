package com.dev7ex.common.bukkit.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 27.02.2023
 */
public class ChatInputCallListener implements Listener {

    private ChatInputCallService chatInputCallService;

    public ChatInputCallListener(@NotNull final ChatInputCallService chatInputCallService) {
        this.chatInputCallService = chatInputCallService;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (this.chatInputCallService.getChatInputCall(player.getUniqueId()) == null) {
           return;
        }
        final ChatInputCall chatInputCall = this.chatInputCallService.getChatInputCall(player.getUniqueId());
        final ChatInputCallOption option = new ChatInputCallOption();

        chatInputCall.call(event.getMessage(), option);

        if (option.isCancel()) {
            event.setCancelled(true);
        }

        if (option.isRemove()) {
            this.chatInputCallService.removeChatCall(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        this.chatInputCallService.removeChatCall(event.getPlayer().getUniqueId());
    }


}
