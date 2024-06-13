package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.plugin.configuration.BasePluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationHolder;

/**
 * Interface representing a configurable BungeeCord plugin.
 * Extends {@link ConfigurationHolder} to support configuration management.
 * <p>
 * Implement this interface in plugins that require configuration loading and management.
 * Use {@link #getConfiguration()} method to retrieve the plugin's configuration instance.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
public interface ConfigurablePlugin extends ConfigurationHolder {

    /**
     * Retrieves the configuration instance associated with this plugin.
     *
     * @param <T> the type of the configuration class that extends {@link BasePluginConfiguration}
     * @return the configuration instance
     */
    <T extends BasePluginConfiguration> T getConfiguration();

}
