package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.bungeecord.plugin.configuration.DefaultPluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationProperties;
import com.dev7ex.common.io.file.configuration.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the configuration for the ProxyCommonPlugin.
 * This class extends {@link DefaultPluginConfiguration} and is annotated with {@link ConfigurationProperties}
 * to specify the file name and configuration provider.
 *
 * @author Dev7ex
 * @since 08.01.2022
 */
@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class ProxyCommonConfiguration extends DefaultPluginConfiguration {

    /**
     * Constructs a new ProxyCommonConfiguration instance.
     *
     * @param plugin the plugin instance this configuration belongs to
     */
    public ProxyCommonConfiguration(@NotNull final ConfigurablePlugin plugin) {
        super(plugin);
    }

}
