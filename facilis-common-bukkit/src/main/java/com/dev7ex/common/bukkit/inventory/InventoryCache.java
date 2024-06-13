package com.dev7ex.common.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class to cache and restore the contents of Bukkit inventories.
 *
 * @author Dev7ex
 * @since 14.05.2024
 */
public class InventoryCache {

    private final ItemStack[] contents;
    private final ItemStack[] storageContents;
    private final ItemStack[] armorContents;
    private final ItemStack[] extraContents;

    /**
     * Constructs an InventoryCache for a generic Inventory.
     *
     * @param inventory The inventory to cache.
     */
    public InventoryCache(@NotNull final Inventory inventory) {
        this.contents = inventory.getContents().clone();
        this.storageContents = inventory.getStorageContents().clone();
        this.armorContents = null;
        this.extraContents = null;
    }

    /**
     * Constructs an InventoryCache for a PlayerInventory.
     *
     * @param inventory The PlayerInventory to cache.
     */
    public InventoryCache(@NotNull final PlayerInventory inventory) {
        this.contents = inventory.getContents().clone();
        this.storageContents = inventory.getStorageContents().clone();
        this.armorContents = inventory.getArmorContents().clone();
        this.extraContents = inventory.getExtraContents().clone();
    }

    /**
     * Restores the cached contents to a generic Inventory.
     *
     * @param inventory The inventory to restore.
     */
    public void restore(@NotNull final Inventory inventory) {
        inventory.setContents(this.contents.clone());
        inventory.setStorageContents(this.storageContents.clone());
    }

    /**
     * Restores the cached contents to a PlayerInventory.
     *
     * @param inventory The PlayerInventory to restore.
     */
    public void restore(@NotNull final PlayerInventory inventory) {
        inventory.setContents(this.contents.clone());
        inventory.setStorageContents(this.storageContents.clone());
        inventory.setArmorContents(this.armorContents.clone());
        inventory.setExtraContents(this.extraContents.clone());
    }

}
