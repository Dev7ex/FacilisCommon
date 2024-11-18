package com.dev7ex.common.bukkit.world.cuboid;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.10.2024
 */
public class Cuboids {

    private Cuboids() {}

    public static Cuboid of(@NotNull final Location firstCorner, @NotNull final Location secondCorner) {
        return new Cuboid(firstCorner, secondCorner);
    }

}
