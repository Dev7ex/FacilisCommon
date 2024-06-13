package com.dev7ex.common.velocity.event.plugin;

import com.dev7ex.common.velocity.plugin.BasePlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Event triggered when a Velocity plugin is loaded.
 * This event can be used to perform any necessary actions when the plugin is being loaded.
 *
 * @since 09.06.2024
 */
public class VelocityPluginLoadEvent extends VelocityPluginEvent {

    /**
     * Constructs a new VelocityPluginLoadEvent with the specified plugin.
     *
     * @param plugin the plugin that is being loaded
     */
    public VelocityPluginLoadEvent(@NotNull final BasePlugin plugin) {
        super(plugin);
    }
}
