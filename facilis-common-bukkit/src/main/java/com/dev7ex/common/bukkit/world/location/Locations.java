package com.dev7ex.common.bukkit.world.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

/**
 * @author Dev7ex
 * @since 08.01.2022
 */
public class Locations {

    private Locations() {}

    public static Location from(final FileConfiguration fileConfiguration, final String path) {
        final String worldName = fileConfiguration.getString(path + ".world");
        final int x = fileConfiguration.getInt(path + ".x");
        final int y = fileConfiguration.getInt(path + ".y");
        final int z = fileConfiguration.getInt(path + ".z");
        final float yaw = (float) fileConfiguration.getDouble(path + ".yaw");
        final float pitch = (float) fileConfiguration.getDouble(path + ".pitch");
        return new Location(Bukkit.getWorld(Objects.requireNonNull(worldName)), x, y, z, yaw, pitch);
    }

    /**
     * @param location the location values be rounded
     * @param roundAxis if yaw and pitch should be rounded
     * @return
     */
    public static Location roundLocation(final Location location, final boolean roundAxis) {
        final double x = Math.round(location.getX());
        final double y = Math.round(location.getY());
        final double z = Math.round(location.getZ());
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        if(roundAxis) {
            yaw = Math.round(location.getYaw());
            pitch = Math.round(location.getPitch());
        }
        return new Location(location.getWorld(), x, y, z, yaw, pitch);
    }

}
