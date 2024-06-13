package com.dev7ex.common.velocity;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;

/**
 * This class provides common utilities for accessing various components of the Velocity proxy server.
 *
 * <p>
 * It allows access to the ProxyServer instance, EventManager, and CommandManager.
 * </p>
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
public class VelocityCommon {

    private static ProxyServer proxyServer;

    /**
     * Retrieves the ProxyServer instance.
     *
     * @return The ProxyServer instance
     */
    @NotNull
    public static ProxyServer getProxyServer() {
        return VelocityCommon.proxyServer;
    }

    /**
     * Sets the ProxyServer instance.
     *
     * @param proxyServer The ProxyServer instance to set
     */
    public static void setProxyServer(@NotNull final ProxyServer proxyServer) {
        VelocityCommon.proxyServer = proxyServer;
    }

    /**
     * Retrieves the EventManager instance.
     *
     * @return The EventManager instance
     */
    @NotNull
    public static EventManager getEventManager() {
        return VelocityCommon.getProxyServer().getEventManager();
    }

    /**
     * Retrieves the CommandManager instance.
     *
     * @return The CommandManager instance
     */
    @NotNull
    public static CommandManager getCommandManager() {
        return VelocityCommon.getProxyServer().getCommandManager();
    }

    @NotNull
    public static PluginManager getPluginManager() {
        return VelocityCommon.getProxyServer().getPluginManager();
    }

}
