package com.dev7ex.common.bukkit.world;

import com.dev7ex.common.bukkit.world.cuboid.Cuboid;
import org.bukkit.Location;

/**
 * Represents a region in a Minecraft world defined by two corner locations or a cuboid.
 * Implementations of this interface should provide methods to retrieve the corner locations
 * or the cuboid representing the region.
 *
 * @author Dev7ex
 * @since 2021-05-19
 */
public interface WorldRegion {

    /**
     * Gets the first corner location of the region.
     *
     * @return The first corner location.
     */
    Location getFirstCorner();

    /**
     * Gets the second corner location of the region.
     *
     * @return The second corner location.
     */
    Location getSecondCorner();

    /**
     * Gets the cuboid representing the region.
     *
     * @return The cuboid representing the region.
     */
    Cuboid getCuboid();

}
