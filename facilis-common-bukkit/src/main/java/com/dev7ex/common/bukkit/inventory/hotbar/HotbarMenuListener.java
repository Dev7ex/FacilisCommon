package com.dev7ex.common.bukkit.inventory.hotbar;

import com.dev7ex.common.bukkit.inventory.hotbar.button.HotbarButton;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
public class HotbarMenuListener implements Listener {

    private final HotbarMenuModule menuModule;

    public HotbarMenuListener(@NotNull final HotbarMenuModule menuModule) {
        this.menuModule = menuModule;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerInventoryClick(final InventoryClickEvent event) {
        if (event.getWhoClicked().getType() != EntityType.PLAYER) {
            return;
        }

        if ((event.getClickedInventory() == null) || (event.getClickedInventory().getType() != InventoryType.PLAYER)) {
            return;
        }
        final Player player = (Player) event.getWhoClicked();


        if (this.menuModule.getOptionalMenu(player.getUniqueId()).isEmpty()) {
            return;
        }
        event.setCancelled(!this.menuModule.getOptionalMenu(player.getUniqueId()).get().isContentsMovable());
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void handlePlayerDropItem(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();

        if (this.menuModule.getOptionalMenu(player.getUniqueId()).isEmpty()) {
            return;
        }
        event.setCancelled(!this.menuModule.getOptionalMenu(player.getUniqueId()).get().isContentsDroppable());
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void handlePlayerPickupItem(final EntityPickupItemEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        final Player player = (Player) event.getEntity();

        if (this.menuModule.getOptionalMenu(player.getUniqueId()).isEmpty()) {
            return;
        }
        event.setCancelled(!this.menuModule.getOptionalMenu(player.getUniqueId()).get().isContentsTakeable());
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void handlePlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (this.menuModule.getOptionalMenu(player.getUniqueId()).isEmpty()) {
            return;
        }

        if ((event.getItem() == null) || (event.getHand() != EquipmentSlot.HAND)) {
            return;
        }
        final HotbarMenu menu = this.menuModule.getOptionalMenu(player.getUniqueId()).get();
        final int clickedSlot = player.getInventory().getHeldItemSlot();

        if (HotbarSlot.getByFixedSlot(clickedSlot).isEmpty()) {
            return;
        }
        final HotbarSlot slot = HotbarSlot.getByFixedSlot(clickedSlot).get();
        final HotbarButton button = menu.getButton(slot);

        button.onClick(player, event.getAction(), event.getItem());
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (this.menuModule.getOptionalMenu(player.getUniqueId()).isEmpty()) {
            return;
        }
        this.menuModule.close(player, true);
    }

}
