package com.dev7ex.common.velocity;

import com.dev7ex.common.io.file.configuration.ConfigurationProperties;
import com.dev7ex.common.io.file.configuration.YamlConfiguration;
import com.dev7ex.common.velocity.plugin.ConfigurablePlugin;
import com.dev7ex.common.velocity.plugin.configuration.DefaultPluginConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the configuration for the VelocityCommon plugin.
 * This class is used to manage configuration properties for the VelocityCommon plugin.
 * It extends DefaultPluginConfiguration and provides functionality to read configuration
 * properties from a YAML file.
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class VelocityCommonConfiguration extends DefaultPluginConfiguration {

    /**
     * Constructs a new VelocityCommonConfiguration object with the specified plugin.
     *
     * @param plugin The ConfigurablePlugin object representing the plugin.
     */
    public VelocityCommonConfiguration(@NotNull final ConfigurablePlugin plugin) {
        super(plugin);
    }

}
