package com.dev7ex.common.bukkit.event.player;

import com.dev7ex.common.bukkit.chat.ChatInputRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 15.05.2024
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PlayerChatInputRequestEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ChatInputRequest request;

    public PlayerChatInputRequestEvent(@NotNull final Player player, @NotNull final ChatInputRequest request) {
        this.player = player;
        this.request = request;

    }

    public static HandlerList getHandlerList() {
        return PlayerChatInputRequestEvent.HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return PlayerChatInputRequestEvent.HANDLERS;
    }

}
