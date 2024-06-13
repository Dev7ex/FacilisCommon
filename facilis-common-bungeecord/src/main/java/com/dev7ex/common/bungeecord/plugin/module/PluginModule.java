package com.dev7ex.common.bungeecord.plugin.module;

/**
 * Interface representing a module in a BungeeCord plugin.
 * Modules can perform specific tasks and integrate with the plugin's lifecycle.
 * <p>
 * Implement this interface to create custom modules that can be enabled or disabled.
 * Each module should define its own initialization and cleanup logic in {@link #onEnable()} and {@link #onDisable()} methods.
 * <p>
 * By default, the module name is derived from its class simple name.
 * Override {@link #getName()} to provide a custom name if needed.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
public interface PluginModule {

    /**
     * Called when the module is enabled.
     * Implement this method to initialize the module's functionality.
     */
    void onEnable();

    /**
     * Called when the module is disabled.
     * Implement this method to clean up resources and deactivate the module.
     */
    void onDisable();

    /**
     * Retrieves the name of the module.
     * By default, returns the simple name of the implementing class.
     * Override this method to provide a custom module name.
     *
     * @return the name of the module
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }

}
