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
public class ChatInputRequestListener implements Listener {

    private final ChatInputRequestService chatInputRequestService;

    public ChatInputRequestListener(@NotNull final ChatInputRequestService chatInputRequestService) {
        this.chatInputRequestService = chatInputRequestService;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (this.chatInputRequestService.getChatInputRequest(player.getUniqueId()) == null) {
           return;
        }
        final ChatInputRequest chatInputRequest = this.chatInputRequestService.getChatInputRequest(player.getUniqueId());
        final ChatInputRequestOption option = new ChatInputRequestOption();

        chatInputRequest.request(event.getMessage(), option);

        if (option.isCancel()) {
            event.setCancelled(true);
        }

        if (option.isRemove()) {
            this.chatInputRequestService.removeChatRequest(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        this.chatInputRequestService.removeChatRequest(event.getPlayer().getUniqueId());
    }


}
