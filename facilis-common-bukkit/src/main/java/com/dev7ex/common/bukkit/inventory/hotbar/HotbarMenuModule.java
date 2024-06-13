package com.dev7ex.common.bukkit.inventory.hotbar;

import com.dev7ex.common.bukkit.event.inventory.hotbar.HotbarMenuCloseEvent;
import com.dev7ex.common.bukkit.event.inventory.hotbar.HotbarMenuOpenEvent;
import com.dev7ex.common.bukkit.inventory.InventoryCache;
import com.dev7ex.common.bukkit.inventory.hotbar.button.HotbarButton;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.module.PluginModule;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
@Getter(AccessLevel.PUBLIC)
public class HotbarMenuModule implements PluginModule {

    private final Map<UUID, HotbarMenu> menus = new HashMap<>();
    private final Map<UUID, InventoryCache> cachedInventories = new HashMap<>();

    public HotbarMenuModule(@NotNull final BukkitPlugin plugin) {
        plugin.registerListener(new HotbarMenuListener(this));
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        for (final Map.Entry<UUID, HotbarMenu> entry : this.menus.entrySet()) {
            if (Bukkit.getPlayer(entry.getKey()) != null) {
                this.close(Objects.requireNonNull(Bukkit.getPlayer(entry.getKey())), true);
            }
        }
        this.menus.clear();
        this.cachedInventories.clear();
    }

    public void open(@NotNull final Player player, final HotbarMenu menu) {
        final PlayerInventory inventory = player.getInventory();
        final InventoryCache inventoryCache = new InventoryCache(player.getInventory());

        Bukkit.getPluginManager().callEvent(new HotbarMenuOpenEvent(player, menu));

        menu.onInitialization();
        this.cachedInventories.put(player.getUniqueId(), inventoryCache);

        for (final Map.Entry<HotbarSlot, HotbarButton> entry : menu.getItems().entrySet()) {
            inventory.setItem(entry.getKey().getFixedSlot(), entry.getValue().getItemStack());
        }
        inventory.setHeldItemSlot(0);
        this.menus.put(player.getUniqueId(), menu);
    }

    public void close(@NotNull final Player player, boolean restoreInventory) {
        Bukkit.getPluginManager().callEvent(new HotbarMenuCloseEvent(player, this.menus.get(player.getUniqueId())));
        player.getInventory().clear();

        if ((this.cachedInventories.get(player.getUniqueId())) != null && (restoreInventory)) {
            this.cachedInventories.get(player.getUniqueId()).restore(player.getInventory());
        }
        this.menus.remove(player.getUniqueId());
    }

    public Optional<HotbarMenu> getOptionalMenu(@NotNull final UUID uniqueId) {
        return Optional.ofNullable(this.menus.get(uniqueId));
    }

}
