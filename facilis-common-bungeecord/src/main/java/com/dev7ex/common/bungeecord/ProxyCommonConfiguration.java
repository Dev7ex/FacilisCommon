package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.configuration.ConfigurationProperties;
import com.dev7ex.common.bungeecord.plugin.configuration.DefaultPluginConfiguration;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class ProxyCommonConfiguration extends DefaultPluginConfiguration {

    public ProxyCommonConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

}
