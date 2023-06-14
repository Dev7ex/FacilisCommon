package com.dev7ex.common.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 27.06.2022
 */
public class BukkitCommon {

    private BukkitCommon() {}

    /**
     * @return The Minecraft version of the server (Example: 1.19.0)
     */
    public static String getMinecraftVersion() {
        String bukkitVersion = Bukkit.getBukkitVersion();
        final int dash = bukkitVersion.indexOf('-');
        return bukkitVersion.substring(0, dash);
    }

    /**
     * @return The NMS version of the server (Example: v1_19_R1)
     */
    public static String getMinecraftServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static CommandSender getCommandSender(@NotNull final String name) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return player;
        }
        return Bukkit.getConsoleSender();
    }

}
