package com.dev7ex.common.bukkit;

import org.bukkit.Bukkit;

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

}
