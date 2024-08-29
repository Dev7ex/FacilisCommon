package com.dev7ex.common.sponge.plugin.module;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public interface PluginModule {

    void onEnable();

    void onDisable();

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
