package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.bungeecord.plugin.ProxyPlugin;
import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatisticProperties;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Represents a common proxy plugin for BungeeCord.
 * This class extends {@link ProxyPlugin} and implements {@link ConfigurablePlugin}.
 * It manages plugin configuration and statistics.
 *
 * @author Dev7ex
 * @since 08.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginStatisticProperties(enabled = true, identification = 23028)
public class ProxyCommonPlugin extends ProxyPlugin implements ConfigurablePlugin {

    private ProxyCommonConfiguration configuration;

    /**
     * Initializes the plugin configuration when the plugin is loaded.
     */
    @Override
    public void onLoad() {
        this.configuration = new ProxyCommonConfiguration(this);
        this.configuration.load();
    }

}
