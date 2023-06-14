package com.dev7ex.common.bukkit.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Dev7ex
 * @since 19.04.2023
 */
public class Inventorys {

    public static Optional<Inventory> getByTitle(@NotNull final Player player, @NotNull final String title) {
        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(title)) {
            return Optional.empty();
        }
        return Optional.of(player.getOpenInventory().getTopInventory());
    }

}
