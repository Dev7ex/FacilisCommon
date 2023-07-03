package com.dev7ex.common.bungeecord;

import com.dev7ex.common.bungeecord.plugin.PluginProperties;
import com.dev7ex.common.bungeecord.plugin.ProxyPlugin;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Dev7ex
 * @since 08.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginProperties(metricsId = 17352, metrics = true)
public class ProxyCommonPlugin extends ProxyPlugin {

    private ProxyCommonConfiguration configuration;

    @Override
    public void onLoad() {
        this.configuration = new ProxyCommonConfiguration(this);
        this.configuration.load();
    }

}
