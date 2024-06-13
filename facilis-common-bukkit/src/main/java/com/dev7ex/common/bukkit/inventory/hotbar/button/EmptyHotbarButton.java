package com.dev7ex.common.bukkit.inventory.hotbar.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
public class EmptyHotbarButton extends HotbarButton {

    public EmptyHotbarButton() {
        super(new ItemStack(Material.AIR));
    }

    @Override
    public void onClick(@NotNull final Player player, @NotNull final Action action, @NotNull final ItemStack itemStack) {}

}
