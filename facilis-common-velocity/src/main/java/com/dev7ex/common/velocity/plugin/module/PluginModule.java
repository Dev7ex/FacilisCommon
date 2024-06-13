package com.dev7ex.common.velocity.plugin.module;

/**
 * @author Dev7ex
 * @since 15.05.2024
 */
/**
 * @author Dev7ex
 * @since 02.03.2024
 */
public interface PluginModule {

    void onEnable();

    void onDisable();

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
