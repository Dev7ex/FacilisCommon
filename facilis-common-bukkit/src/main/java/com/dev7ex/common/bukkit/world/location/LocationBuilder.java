package com.dev7ex.common.bukkit.world.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 11.03.2024
 */
public class LocationBuilder {

    private World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Vector direction;
    public LocationBuilder(@NotNull final World world, final double x,
                           final double y, final double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public LocationBuilder(@NotNull final String worldName, final double x,
                           final double y, final double z) {
        this.world = Bukkit.getWorld(worldName);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public LocationBuilder(@NotNull final World world, final double x,
                           final double y, final double z,
                           final float yaw, final float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public LocationBuilder(@NotNull final String worldName, final double x,
                           final double y, final double z,
                           final float yaw, final float pitch) {
        this.world = Bukkit.getWorld(worldName);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public LocationBuilder(@NotNull final World world) {
        this.world = world;
    }
    public LocationBuilder(@NotNull final String worldName) {
        this.world = Bukkit.getWorld(worldName);
    }
    public LocationBuilder setX(final double x) {
        this.x = x;
        return this;
    }
    public LocationBuilder setY(final double y) {
        this.y = y;
        return this;
    }
    public LocationBuilder setZ(final double z) {
        this.z = z;
        return this;
    }
    public LocationBuilder setYaw(final float yaw) {
        this.yaw = yaw;
        return this;
    }
    public LocationBuilder setPitch(final float pitch) {
        this.pitch = pitch;
        return this;
    }
    public LocationBuilder setDirection(final Vector direction) {
        this.direction = direction;
        return this;
    }
    public Location build() {
        final Location location = new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch);

        if (this.direction != null) {
            location.setDirection(this.direction);
        }
        return location;
    }

}
