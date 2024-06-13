package com.dev7ex.common.bukkit.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for common operations on Bukkit worlds.
 * Provides methods to retrieve Nether and End worlds, and check if a world is loaded.
 *
 * @author Dev7ex
 * @since 08.01.2022
 */
public class Worlds {

    /**
     * Retrieves all Overworld worlds currently loaded on the server.
     *
     * @return A list of Overworld worlds.
     */
    public static List<World> getOverworldWorlds() {
        return Bukkit.getWorlds().stream()
                .filter(world -> world.getEnvironment().equals(World.Environment.NORMAL))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all Nether worlds currently loaded on the server.
     *
     * @return A list of Nether worlds.
     */
    public static List<World> getNetherWorlds() {
        return Bukkit.getWorlds().stream()
                .filter(world -> world.getEnvironment().equals(World.Environment.NETHER))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all End worlds currently loaded on the server.
     *
     * @return A list of End worlds.
     */
    public static List<World> getEndWorlds() {
        return Bukkit.getWorlds().stream()
                .filter(world -> world.getEnvironment().equals(World.Environment.THE_END))
                .collect(Collectors.toList());
    }

    /**
     * Checks if a world with the given name is loaded on the server.
     *
     * @param worldName The name of the world to check.
     * @return {@code true} if the world is loaded, {@code false} otherwise.
     */
    public static boolean isLoaded(@NotNull final String worldName) {
        return Bukkit.getWorld(worldName) != null;
    }

}
