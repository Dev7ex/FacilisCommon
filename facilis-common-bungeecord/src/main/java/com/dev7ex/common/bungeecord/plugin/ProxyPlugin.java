package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatistic;
import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatisticProperties;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Abstract base class for BungeeCord plugins.
 * Provides common lifecycle methods and utilities for plugin initialization and management.
 * Extend this class to implement specific BungeeCord plugin functionality.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
public abstract class ProxyPlugin extends BasePlugin {

    private PluginStatistic statistic;

    /**
     * Called when the plugin is enabled.
     * Registers commands, listeners, and modules. Optionally initializes statistics and database connections.
     */
    @Override
    public void onEnable() {
        this.registerModules();
        this.registerCommands();
        this.registerListeners();
        this.registerTasks();

        // Initialize statistics if enabled
        if (this.hasStatistics()) {
            final PluginStatisticProperties statisticProperties = this.getStatisticProperties();

            if (!statisticProperties.enabled()) {
                return; // Statistics are disabled, do not initialize
            }
            this.statistic = new PluginStatistic(this, statisticProperties.identification());
        }

        // Initialize database connection if the plugin supports it
        if (this.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) this;
            databasePlugin.onConnect();
        }

        super.getModuleManager().enableAllModules();
    }

    /**
     * Called when the plugin is disabled.
     * Disconnects from the database if applicable and disables all registered modules.
     */
    @Override
    public void onDisable() {
        if (this.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) this;
            databasePlugin.onDisconnect();
        }

        super.getModuleManager().disableAllModules();
    }

    /**
     * Retrieves a plugin instance by its class type.
     *
     * @param clazz the class of the plugin to retrieve
     * @param <T>   the type of the plugin
     * @return an Optional containing the plugin instance if found, or throws an exception if not found
     */
    public static <T extends Plugin> T getPlugin(@NotNull final Class<? extends Plugin> clazz) {
        final Optional<Plugin> plugin = ProxyServer.getInstance().getPluginManager().getPlugins()
                .stream()
                .filter(searchedPlugin -> searchedPlugin.getClass() == clazz)
                .findFirst();
        return (T) plugin.orElseThrow(); // Return the found plugin or throw an exception if not found
    }

}
