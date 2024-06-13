package com.dev7ex.common.bukkit.event.inventory.hotbar;

import com.dev7ex.common.bukkit.inventory.hotbar.HotbarMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 15.05.2024
 */
public class HotbarMenuOpenEvent extends HotbarMenuEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public HotbarMenuOpenEvent(@NotNull final Player player, @NotNull final HotbarMenu menu) {
        super(player, menu);
    }

    public static HandlerList getHandlerList() {
        return HotbarMenuOpenEvent.HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HotbarMenuOpenEvent.HANDLERS;
    }

}
