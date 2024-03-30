package com.dev7ex.common.bukkit.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public static boolean isSimilar(@Nullable final Inventory firstInventory, @Nullable final Inventory secondInventory) {
        return ((firstInventory != null) && (secondInventory != null)) && ((firstInventory.getHolder()) != (secondInventory.getHolder()));
    }

}
