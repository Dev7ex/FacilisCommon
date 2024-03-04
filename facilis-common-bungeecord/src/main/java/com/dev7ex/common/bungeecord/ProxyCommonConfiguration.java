package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.bungeecord.plugin.configuration.DefaultPluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationProperties;
import com.dev7ex.common.io.file.configuration.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class ProxyCommonConfiguration extends DefaultPluginConfiguration {

    public ProxyCommonConfiguration(@NotNull final ConfigurablePlugin plugin) {
        super(plugin);
    }

}
