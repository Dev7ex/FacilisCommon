package com.dev7ex.common.bukkit.inventory.hotbar;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Dev7ex
 * @since 14.05.2024
 */
@Getter(AccessLevel.PUBLIC)
public enum HotbarSlot {

    FIRST(0),
    SECOND(1),
    THIRD(2),
    FOURTH(3),
    FIFTH(4),
    SIXTH(5),
    SEVENTH(6),
    EIGHT(7),
    NINTH(8);

    private final int fixedSlot;

    HotbarSlot(final int fixedSlot) {
        this.fixedSlot = fixedSlot;
    }

    public static Optional<HotbarSlot> getByFixedSlot(final int slot) {
        return Arrays.stream(HotbarSlot.values()).filter(hotbarSlot -> hotbarSlot.getFixedSlot() == slot).findFirst();
    }

}
