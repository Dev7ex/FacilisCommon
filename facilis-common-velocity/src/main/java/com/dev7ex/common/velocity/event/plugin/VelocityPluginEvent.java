package com.dev7ex.common.velocity.event.plugin;

import com.dev7ex.common.velocity.plugin.BasePlugin;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for events related to Velocity plugins.
 * This class is intended to be extended by other classes that represent specific plugin events.
 *
 * @since 09.06.2024
 */
@Getter(AccessLevel.PUBLIC)
public class VelocityPluginEvent {

    /**
     * The plugin associated with this event.
     */
    private final BasePlugin plugin;

    /**
     * Constructs a new VelocityPluginEvent with the specified plugin.
     *
     * @param plugin the plugin associated with this event
     */
    public VelocityPluginEvent(@NotNull final BasePlugin plugin) {
        this.plugin = plugin;
    }

}
