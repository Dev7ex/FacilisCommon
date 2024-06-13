package com.dev7ex.common.bukkit.chat;

import com.dev7ex.common.bukkit.BukkitCommonPlugin;
import com.dev7ex.common.bukkit.event.player.PlayerChatInputRequestEvent;
import org.bukkit.Bukkit;
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

    private final ChatInputRequestModule chatInputRequestModule;

    public ChatInputRequestListener(@NotNull final ChatInputRequestModule chatInputRequestModule) {
        this.chatInputRequestModule = chatInputRequestModule;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (this.chatInputRequestModule.getChatInputRequest(player.getUniqueId()) == null) {
            return;
        }
        final ChatInputRequest chatInputRequest = this.chatInputRequestModule.getChatInputRequest(player.getUniqueId());
        final ChatInputRequestOption option = new ChatInputRequestOption();

        Bukkit.getScheduler().runTask(BukkitCommonPlugin.getInstance(), () -> {
            Bukkit.getPluginManager().callEvent(new PlayerChatInputRequestEvent(player, chatInputRequest));
        });
        chatInputRequest.request(event.getMessage(), option);

        if (option.isCancel()) {
            event.setCancelled(true);
        }

        if (option.isRemove()) {
            this.chatInputRequestModule.removeChatRequest(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        this.chatInputRequestModule.removeChatRequest(event.getPlayer().getUniqueId());
    }

}
