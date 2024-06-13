package com.dev7ex.common.velocity.plugin;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * Represents a base class for Velocity plugins.
 * This class provides common functionality and serves as a foundation for developing Velocity plugins.
 * It initializes the plugin with essential components such as the server, logger, and data directory.
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
public abstract class VelocityPlugin extends BasePlugin {

    /**
     * Constructs a new VelocityPlugin object with the specified parameters.
     *
     * @param server        The ProxyServer object representing the Velocity server.
     * @param logger        The Logger object for logging messages.
     * @param dataDirectory The path to the data directory.
     */
    public VelocityPlugin(@NotNull final ProxyServer server, @NotNull final Logger logger, final Path dataDirectory) {
        super(server, logger, dataDirectory);
    }

    @Subscribe
    @Override
    public void onInitialize(final ProxyInitializeEvent event) {
        super.onInitialize(event);
    }

    @Subscribe
    @Override
    public void onShutdown(final ProxyShutdownEvent event) {
        super.onShutdown(event);
    }

}
