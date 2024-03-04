package com.dev7ex.common.bukkit.plugin.module;

/**
 * @author Dev7ex
 * @since 12.07.2022
 */
public interface PluginModule {

    void onEnable();

    void onDisable();

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
