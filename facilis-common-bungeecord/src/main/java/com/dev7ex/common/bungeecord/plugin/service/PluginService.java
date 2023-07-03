package com.dev7ex.common.bungeecord.plugin.service;

/**
 * @author Dev7ex
 * @since 12.07.2022
 */
public interface PluginService {

    void onEnable();

    void onDisable();

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
