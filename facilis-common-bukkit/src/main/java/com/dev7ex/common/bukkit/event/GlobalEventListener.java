package com.dev7ex.common.bukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public abstract class GlobalEventListener implements Listener {

    public GlobalEventListener(@NotNull final JavaPlugin plugin, final boolean ignoreCancelled) {
        new RegisteredListener(this, (listener, event) ->
                this.handleEvent(event), EventPriority.NORMAL, plugin, ignoreCancelled);
    }

    public abstract void handleEvent(@NotNull final Event event);

}
