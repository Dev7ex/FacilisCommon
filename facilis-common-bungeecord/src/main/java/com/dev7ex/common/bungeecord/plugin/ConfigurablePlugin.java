package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.plugin.configuration.BasePluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationHolder;

/**
 * @author Dev7ex
 * @since 02.03.2024
 */
public interface ConfigurablePlugin extends ConfigurationHolder {

    <T extends BasePluginConfiguration> T getConfiguration();

}
