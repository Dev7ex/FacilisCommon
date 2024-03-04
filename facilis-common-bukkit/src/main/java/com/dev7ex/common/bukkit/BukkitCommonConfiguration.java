package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationHolder;
import com.dev7ex.common.io.file.configuration.ConfigurationProperties;
import com.dev7ex.common.io.file.configuration.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class BukkitCommonConfiguration extends DefaultPluginConfiguration {

    public static String VERSION = "1.0.2-SNAPSHOT";

    public BukkitCommonConfiguration(@NotNull final ConfigurationHolder configurationHolder) {
        super(configurationHolder);
    }

    @Override
    public void load() {
        super.load();
    }

}
