package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.plugin.BungeePlugin;
import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatisticProperties;
import lombok.AccessLevel;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Listener;

/**
 * Represents a common proxy plugin for BungeeCord.
 * This class extends {@link BungeePlugin} and implements {@link ConfigurablePlugin}.
 * It manages plugin configuration and statistics.
 *
 * @author Dev7ex
 * @since 08.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginStatisticProperties(enabled = true, identification = 23028)
public class BungeeCommonPlugin extends BungeePlugin implements ConfigurablePlugin, Listener {

    private BungeeCommonConfiguration configuration;

    /**
     * Initializes the plugin configuration when the plugin is loaded.
     */
    @Override
    public void onLoad() {
        this.configuration = new BungeeCommonConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        super.registerListener(this);
    }

}
