package com.dev7ex.common.bukkit.inventory.hotbar;

import com.dev7ex.common.bukkit.inventory.InventoryCache;
import com.dev7ex.common.bukkit.inventory.hotbar.button.HotbarButton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class HotbarMenu {

    private final Map<HotbarSlot, HotbarButton> items = new HashMap<>();
    private boolean contentsDroppable = true;
    private boolean contentsTakeable = true;
    private boolean contentsMovable = true;
    private Player player;
    private InventoryCache inventoryCache;

    public HotbarMenu(@NotNull final Player player) {
        this.player = player;
    }

    public abstract void onInitialization();

    public void setButton(@NotNull final HotbarSlot slot, final HotbarButton button) {
        this.items.put(slot, button);
    }

    public HotbarButton getButton(@NotNull final HotbarSlot slot) {
        return this.items.get(slot);
    }

}
