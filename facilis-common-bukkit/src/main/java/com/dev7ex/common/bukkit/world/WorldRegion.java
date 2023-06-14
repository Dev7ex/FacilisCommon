package com.dev7ex.common.bukkit.world;

import com.dev7ex.common.bukkit.world.cuboid.Cuboid;

import org.bukkit.Location;

/**
 * @author Dev7ex
 * @since 19.05.2021
 */
public interface WorldRegion {

    Location getFirstCorner();

    Location getSecondCorner();

    Cuboid getCuboid();

}
