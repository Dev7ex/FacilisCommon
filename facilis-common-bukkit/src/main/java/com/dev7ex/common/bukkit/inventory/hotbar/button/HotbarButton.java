package com.dev7ex.common.bukkit.inventory.hotbar.button;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class HotbarButton {

    private final ItemStack itemStack;

    public HotbarButton(@NotNull final ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public abstract void onClick(@NotNull final Player player, @NotNull final Action action, @NotNull final ItemStack itemStack);

}
