package com.dev7ex.common.velocity.event.plugin;

import com.dev7ex.common.velocity.plugin.BasePlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Event triggered when a Velocity plugin is disabled.
 * This event can be used to perform cleanup or other necessary actions when the plugin is being disabled.
 *
 * @since 09.06.2024
 */
public class VelocityPluginDisableEvent extends VelocityPluginEvent {

    /**
     * Constructs a new VelocityPluginDisableEvent with the specified plugin.
     *
     * @param plugin the plugin that is being disabled
     */
    public VelocityPluginDisableEvent(@NotNull final BasePlugin plugin) {
        super(plugin);
    }
}
