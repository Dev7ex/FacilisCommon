package com.dev7ex.common.velocity.plugin;

import com.dev7ex.common.io.file.configuration.ConfigurationHolder;
import com.dev7ex.common.velocity.plugin.configuration.BasePluginConfiguration;

/**
 * Represents a plugin interface for plugins that require configuration.
 * This interface extends ConfigurationHolder, allowing plugins to hold and manage configurations.
 * It defines a method to retrieve the configuration object of a specific type.
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
public interface ConfigurablePlugin extends ConfigurationHolder {

    /**
     * Retrieves the configuration object of the specified type.
     *
     * @param <T> The type of the configuration object.
     * @return The configuration object.
     */
    <T extends BasePluginConfiguration> T getConfiguration();

}
