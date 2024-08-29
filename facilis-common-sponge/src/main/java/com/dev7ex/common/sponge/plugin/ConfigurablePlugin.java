package com.dev7ex.common.sponge.plugin;

import com.dev7ex.common.io.file.configuration.ConfigurationHolder;
import com.dev7ex.common.sponge.plugin.configuration.BasePluginConfiguration;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public interface ConfigurablePlugin extends ConfigurationHolder {

    <T extends BasePluginConfiguration> T getConfiguration();

}
