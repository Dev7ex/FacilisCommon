package com.dev7ex.common.bukkit.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Dev7ex
 * @since 08.01.2022
 */
public class Worlds {

    public static List<World> getNetherWorlds() {
        return Bukkit.getWorlds().stream().filter(world -> world.getEnvironment().equals(World.Environment.NETHER)).toList();
    }

    public static List<World> getEndWorlds() {
        return Bukkit.getWorlds().stream().filter(world -> world.getEnvironment().equals(World.Environment.THE_END)).toList();
    }
    public static boolean isLoaded(@NotNull final String worldName) {
        return (Bukkit.getWorld(worldName) != null);
    }

}
