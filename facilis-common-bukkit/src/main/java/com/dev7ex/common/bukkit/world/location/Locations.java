package com.dev7ex.common.bukkit.world.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A utility class for handling {@link Location} related operations in Bukkit.
 * This class provides methods for creating locations from configuration files,
 * rounding location coordinates, comparing locations, and more.
 *
 * <p>
 * Note: This class cannot be instantiated.
 * </p>
 *
 * @author Dev7ex
 * @since 08.01.2022
 */
public final class Locations {

    private Locations() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates a {@link Location} object from a configuration file.
     *
     * @param fileConfiguration the configuration file to read from
     * @param path              the path in the configuration file where the location data is stored
     * @return the created {@link Location} object, or null if the world is not found
     */
    @Nullable
    public static Location from(@NotNull final FileConfiguration fileConfiguration, @NotNull final String path) {
        final String worldName = fileConfiguration.getString(path + ".world");
        final double x = fileConfiguration.getDouble(path + ".x");
        final double y = fileConfiguration.getDouble(path + ".y");
        final double z = fileConfiguration.getDouble(path + ".z");
        final float yaw = (float) fileConfiguration.getDouble(path + ".yaw");
        final float pitch = (float) fileConfiguration.getDouble(path + ".pitch");

        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

    /**
     * Rounds the coordinates of a given location.
     *
     * @param location  the location whose coordinates should be rounded
     * @param roundAxis if true, the yaw and pitch will also be rounded
     * @return a new {@link Location} object with rounded coordinates
     */
    @NotNull
    public static Location roundLocation(@NotNull final Location location, final boolean roundAxis) {
        final double x = Math.round(location.getX());
        final double y = Math.round(location.getY());
        final double z = Math.round(location.getZ());
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        if (roundAxis) {
            yaw = Math.round(yaw);
            pitch = Math.round(pitch);
        }
        return new Location(location.getWorld(), x, y, z, yaw, pitch);
    }

    /**
     * Checks if two locations are similar, comparing their world and block coordinates.
     *
     * @param firstLocation  the first location to compare
     * @param secondLocation the second location to compare
     * @return true if the locations are in the same world and have the same block coordinates, false otherwise
     */
    public static boolean isSimilar(@NotNull final Location firstLocation, @NotNull final Location secondLocation) {
        if ((firstLocation.getWorld() == null) || (secondLocation.getWorld() == null)) {
            return false;
        }
        return firstLocation.getWorld().equals(secondLocation.getWorld()) &&
                firstLocation.getBlockX() == secondLocation.getBlockX() &&
                firstLocation.getBlockY() == secondLocation.getBlockY() &&
                firstLocation.getBlockZ() == secondLocation.getBlockZ();
    }

    /**
     * Converts a {@link Location} to a formatted string.
     *
     * @param location the location to convert
     * @return a string representation of the location in the format "world,x,y,z,yaw,pitch"
     */
    @NotNull
    public static String toString(@NotNull final Location location) {
        return String.format("%s,%f,%f,%f,%f,%f",
                Objects.requireNonNull(location.getWorld()).getName(),
                location.getX(), location.getY(), location.getZ(),
                location.getYaw(), location.getPitch());
    }

    /**
     * Creates a {@link Location} from a formatted string.
     *
     * @param locationString the string representation of the location in the format "world,x,y,z,yaw,pitch"
     * @return the created {@link Location} object, or null if the world is not found
     */
    @Nullable
    public static Location fromString(@NotNull final String locationString) {
        final String[] parts = locationString.split(",");

        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid location string format. Expected format: world,x,y,z,yaw,pitch");
        }
        final String worldName = parts[0];
        final double x = Double.parseDouble(parts[1]);
        final double y = Double.parseDouble(parts[2]);
        final double z = Double.parseDouble(parts[3]);
        final float yaw = Float.parseFloat(parts[4]);
        final float pitch = Float.parseFloat(parts[5]);
        final World world = Bukkit.getWorld(worldName);

        if (world == null) {
            return null;
        }
        return new Location(world, x, y, z, yaw, pitch);
    }

    /**
     * Checks if a location is within a specified range of another location.
     *
     * @param firstLocation  the first location
     * @param secondLocation the second location
     * @param range          the range to check within
     * @return true if loc1 is within the specified range of loc2, false otherwise
     */
    public static boolean isWithinRange(@NotNull final Location firstLocation, @NotNull final Location secondLocation, final double range) {
        if (firstLocation.getWorld() == null || secondLocation.getWorld() == null) {
            return false;
        }

        if (!firstLocation.getWorld().equals(secondLocation.getWorld())) {
            return false;
        }

        return firstLocation.distance(secondLocation) <= range;
    }

}
