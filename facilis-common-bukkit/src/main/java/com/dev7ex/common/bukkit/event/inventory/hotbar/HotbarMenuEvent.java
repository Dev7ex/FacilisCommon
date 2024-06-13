package com.dev7ex.common.bukkit.event.inventory.hotbar;

import com.dev7ex.common.bukkit.inventory.hotbar.HotbarMenu;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 15.05.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class HotbarMenuEvent extends Event {

    private final Player player;
    private final HotbarMenu menu;

    public HotbarMenuEvent(@NotNull final Player player, @NotNull final HotbarMenu menu) {
        this.player = player;
        this.menu = menu;
    }

}
