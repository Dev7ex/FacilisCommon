package com.dev7ex.common.velocity.event;

import com.dev7ex.common.velocity.plugin.ConfigurablePlugin;
import com.dev7ex.common.velocity.plugin.VelocityPlugin;
import com.dev7ex.common.velocity.plugin.configuration.BasePluginConfiguration;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AccessLevel;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract class representing a listener for Velocity events.
 * This class is intended to be extended by other classes that wish to listen for events in a Velocity plugin.
 *
 * @since 09.06.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class VelocityListener {

    /**
     * The plugin instance associated with this listener.
     */
    private final VelocityPlugin plugin;

    /**
     * Constructs a new VelocityListener with the specified plugin.
     *
     * @param plugin the plugin associated with this listener
     */
    public VelocityListener(@NotNull final VelocityPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Returns the ProxyServer instance from the plugin.
     *
     * @return the ProxyServer instance
     */
    public ProxyServer getServer() {
        return this.plugin.getServer();
    }

    /**
     * Returns the configuration of the plugin.
     *
     * @param <T> the type of the plugin configuration
     * @return the configuration of the plugin
     */
    public <T extends BasePluginConfiguration> T getConfiguration() {
        return ((ConfigurablePlugin) this.plugin).getConfiguration();
    }

    /**
     * Returns the MiniMessage instance for message formatting.
     *
     * @return the MiniMessage instance
     */
    public MiniMessage getMiniMessage() {
        return MiniMessage.miniMessage();
    }

}
