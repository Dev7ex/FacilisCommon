package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@ConfigurationProperties(fileName = "config.yml")
public class BukkitCommonConfiguration extends DefaultPluginConfiguration {

    public BukkitCommonConfiguration(final Plugin plugin) {
        super(plugin);
    }
}
