package com.dev7ex.common.bukkit.world.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * A builder class for creating {@link Location} objects in Bukkit.
 * This class provides a fluent API to set various properties of a location
 * such as coordinates, yaw, pitch, and direction.
 *
 * @author Dev7ex
 * @since 11.03.2024
 */
public class LocationBuilder {

    private final World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Vector direction;

    /**
     * Constructs a {@code LocationBuilder} with the specified world and coordinates.
     *
     * @param world the world where the location is
     * @param x     the X coordinate of the location
     * @param y     the Y coordinate of the location
     * @param z     the Z coordinate of the location
     */
    public LocationBuilder(@NotNull final World world, final double x,
                           final double y, final double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a {@code LocationBuilder} with the specified world name and coordinates.
     *
     * @param worldName the name of the world where the location is
     * @param x         the X coordinate of the location
     * @param y         the Y coordinate of the location
     * @param z         the Z coordinate of the location
     */
    public LocationBuilder(@NotNull final String worldName, final double x,
                           final double y, final double z) {
        this.world = Bukkit.getWorld(worldName);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a {@code LocationBuilder} with the specified world, coordinates, yaw, and pitch.
     *
     * @param world the world where the location is
     * @param x     the X coordinate of the location
     * @param y     the Y coordinate of the location
     * @param z     the Z coordinate of the location
     * @param yaw   the yaw (rotation around the vertical axis) of the location
     * @param pitch the pitch (rotation around the horizontal axis) of the location
     */
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

    /**
     * Constructs a {@code LocationBuilder} with the specified world name, coordinates, yaw, and pitch.
     *
     * @param worldName the name of the world where the location is
     * @param x         the X coordinate of the location
     * @param y         the Y coordinate of the location
     * @param z         the Z coordinate of the location
     * @param yaw       the yaw (rotation around the vertical axis) of the location
     * @param pitch     the pitch (rotation around the horizontal axis) of the location
     */
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

    /**
     * Constructs a {@code LocationBuilder} with the specified world.
     *
     * @param world the world where the location is
     */
    public LocationBuilder(@NotNull final World world) {
        this.world = world;
    }

    /**
     * Constructs a {@code LocationBuilder} with the specified world name.
     *
     * @param worldName the name of the world where the location is
     */
    public LocationBuilder(@NotNull final String worldName) {
        this.world = Bukkit.getWorld(worldName);
    }

    /**
     * Sets the X coordinate of the location.
     *
     * @param x the X coordinate to set
     * @return this builder
     */
    public LocationBuilder setX(final double x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Y coordinate of the location.
     *
     * @param y the Y coordinate to set
     * @return this builder
     */
    public LocationBuilder setY(final double y) {
        this.y = y;
        return this;
    }

    /**
     * Sets the Z coordinate of the location.
     *
     * @param z the Z coordinate to set
     * @return this builder
     */
    public LocationBuilder setZ(final double z) {
        this.z = z;
        return this;
    }

    /**
     * Sets the yaw (rotation around the vertical axis) of the location.
     *
     * @param yaw the yaw to set
     * @return this builder
     */
    public LocationBuilder setYaw(final float yaw) {
        this.yaw = yaw;
        return this;
    }

    /**
     * Sets the pitch (rotation around the horizontal axis) of the location.
     *
     * @param pitch the pitch to set
     * @return this builder
     */
    public LocationBuilder setPitch(final float pitch) {
        this.pitch = pitch;
        return this;
    }

    /**
     * Sets the direction of the location.
     *
     * @param direction the direction to set
     * @return this builder
     */
    public LocationBuilder setDirection(final Vector direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Builds and returns the {@link Location} object based on the set properties.
     *
     * @return the constructed {@link Location} object
     */
    public Location build() {
        final Location location = new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch);

        if (this.direction != null) {
            location.setDirection(this.direction);
        }
        return location;
    }

}
