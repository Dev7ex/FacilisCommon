package com.dev7ex.common.bukkit.world;

import com.dev7ex.common.bukkit.world.cuboid.Cuboid;

import lombok.AccessLevel;
import lombok.Getter;

import org.bukkit.Location;

/**
 * @author Dev7ex
 * @since 19.05.2021
 */
@Getter(AccessLevel.PUBLIC)
public class WorldRegion {

    private final Location firstLocation;
    private final Location secondLocation;
    private final Cuboid cuboid;

    public WorldRegion(final Location firstLocation, final Location secondLocation) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.cuboid = new Cuboid(firstLocation, secondLocation);
    }

}
