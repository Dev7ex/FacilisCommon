package com.dev7ex.common.velocity.event.plugin;

import com.dev7ex.common.velocity.plugin.BasePlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Event triggered when a Velocity plugin is enabled.
 * This event can be used to perform initialization or other necessary actions when the plugin is being enabled.
 *
 * @since 09.06.2024
 */
public class VelocityPluginEnableEvent extends VelocityPluginEvent {

    /**
     * Constructs a new VelocityPluginEnableEvent with the specified plugin.
     *
     * @param plugin the plugin that is being enabled
     */
    public VelocityPluginEnableEvent(@NotNull final BasePlugin plugin) {
        super(plugin);
    }

}
