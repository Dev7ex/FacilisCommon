package com.dev7ex.common.bukkit.craftbukkit.v1_20_R3;

import com.dev7ex.common.bukkit.craftbukkit.CraftBukkitCommon;
import com.google.common.annotations.Beta;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;

/**
 * @author Dev7ex
 * @since 30.03.2024
 */
@Beta
public class CraftBukkitCommon_V1_20_R3 implements CraftBukkitCommon {

    @Override
    public MinecraftServer getMinecraftServer() {
        return ((CraftServer) Bukkit.getServer()).getServer();
    }

    @Override
    public CraftWorld getCraftWorld(final String name) {
        return (CraftWorld) Bukkit.getWorld(name);
    }

}
