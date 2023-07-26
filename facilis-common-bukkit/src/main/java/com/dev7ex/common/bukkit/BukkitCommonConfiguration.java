package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;

import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@ConfigurationProperties(fileName = "config.yml")
public class BukkitCommonConfiguration extends DefaultPluginConfiguration {

    public static String VERSION = "1.0.2-SNAPSHOT";

    public BukkitCommonConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        if (!super.getVersionAsString().equalsIgnoreCase(BukkitCommonConfiguration.VERSION)) {
            super.deleteFile();
        }
        super.load();
    }

}
