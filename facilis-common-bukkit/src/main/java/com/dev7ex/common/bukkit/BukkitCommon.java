package com.dev7ex.common.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author Dev7ex
 * @since 27.06.2022
 */
public class BukkitCommon {

    private BukkitCommon() {
    }

    /**
     * @return The Minecraft version of the server (Example: 1.19.0)
     */
    public static String getMinecraftVersion() {
        final String bukkitVersion = Bukkit.getBukkitVersion();
        final int dash = bukkitVersion.indexOf('-');
        return bukkitVersion.substring(0, dash);
    }

    /**
     * @return The NMS version of the server (Example: v1_19_R1)
     */
    public static String getMinecraftServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static boolean isPaper() {
        try {
            Class.forName("io.papermc.paper.event.player.AsyncChatEvent");
            return true;

        } catch (final ClassNotFoundException exception) {
            return false;
        }
    }

    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;

        } catch (final ClassNotFoundException exception) {
            return false;
        }
    }

    /**
     * Gets the type of server software.
     *
     * @return a string representing the server software. It could be "Paper", "Folia", or "Spigot".
     */
    public static String getServerSoftware() {
        if (isPaper()) {
            return "Paper";
        } else if (isFolia()) {
            return "Folia";
        }
        return "Spigot";
    }

    /**
     * Gets a {@link CommandSender} by name.
     *
     * @param name the name of the player or console.
     * @return the {@link CommandSender} corresponding to the given name. If a player with the given name is found, that player is returned.
     * Otherwise, the console sender is returned.
     */
    public static CommandSender getCommandSender(@NotNull final String name) {
        final Player player = Bukkit.getPlayerExact(name);
        return (player != null) ? player : Bukkit.getConsoleSender();
    }

    public static Locale getLocale(@NotNull final CommandSender commandSender) {
        if (commandSender instanceof final Player player) {
            final String[] parts = player.getLocale().split("_");
            return new Locale(parts[0], parts[1]);
        }
        return Locale.ENGLISH;
    }

}
