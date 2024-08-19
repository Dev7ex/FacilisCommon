package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.command.BungeeCommand;
import com.dev7ex.common.bungeecord.command.BungeeCommandExecutor;
import com.dev7ex.common.bungeecord.plugin.module.PluginModule;
import com.dev7ex.common.bungeecord.plugin.module.PluginModuleManager;
import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatisticProperties;
import lombok.AccessLevel;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.function.Predicate;

/**
 * Base class for BungeeCord plugins providing common functionality and utilities.
 * Extend this class to implement specific plugin functionalities.
 * <p>
 * Manages registration of commands, listeners, modules, and plugin lifecycle tasks.
 * Provides methods for managing plugin data folders and registering plugin components.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
@Getter(AccessLevel.PUBLIC)
public class BasePlugin extends Plugin {

    private final PluginModuleManager moduleManager = new PluginModuleManager(this);
    private final PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();

    /**
     * Registers commands for the plugin.
     * Override this method in subclasses to register custom commands.
     */
    public void registerCommands() {
        // Implement in subclasses
    }

    /**
     * Registers listeners for the plugin.
     * Override this method in subclasses to register custom listeners.
     */
    public void registerListeners() {
        // Implement in subclasses
    }

    /**
     * Registers modules for the plugin.
     * Override this method in subclasses to register custom modules.
     */
    public void registerModules() {
        // Implement in subclasses
    }

    /**
     * Registers tasks for the plugin.
     * Override this method in subclasses to register custom tasks.
     */
    public void registerTasks() {
        // Implement in subclasses
    }

    /**
     * Creates the data folder for the plugin if it does not exist.
     * The data folder is a directory where the plugin stores its configuration
     * files, data, or any other persistent information.
     * If the folder already exists, this method does nothing.
     * If the folder creation fails, an exception may be thrown.
     *
     * @since 1.0.3
     */
    public void createDataFolder() {
        if (super.getDataFolder().exists()) {
            return;
        }
        super.getDataFolder().mkdirs();
    }

    /**
     * Creates a subfolder within the designated parent directory if it does not exist.
     * If the subfolder already exists, this method does nothing.
     *
     * @param folderName the name of the subfolder to create
     * @since 1.0.3
     */
    public void createSubFolder(@NotNull final String folderName) {
        final File subFolder = new File(this.getDataFolder().getPath() + File.separator + folderName);
        if (subFolder.exists()) {
            return;
        }
        subFolder.mkdirs();
    }

    /**
     * Retrieves a subfolder within the designated parent directory.
     * If the subfolder does not exist, this method returns null.
     *
     * @param folderName the name of the subfolder to retrieve
     * @return the File object representing the subfolder, or null if it does not exist
     * @since 1.0.3
     */
    public File getSubFolder(@NotNull final String folderName) {
        return new File(this.getDataFolder().getPath() + File.separator + folderName);
    }

    /**
     * Registers a command with the plugin.
     *
     * @param command the command to register
     */
    public void registerCommand(@NotNull final Command command) {
        super.getProxy().getPluginManager().registerCommand(this, command);
    }

    /**
     * Registers a command with the plugin if a condition is met.
     *
     * @param command   the command to register
     * @param predicate the condition that must be true to register the command
     */
    public void registerCommandIf(@NotNull final Command command, final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        super.getProxy().getPluginManager().registerCommand(this, command);
    }

    /**
     * Registers a proxy command with the plugin.
     *
     * @param bungeeCommand the proxy command to register
     */
    public void registerCommand(@NotNull final BungeeCommand bungeeCommand) {
        super.getProxy().getPluginManager().registerCommand(this, new BungeeCommandExecutor(bungeeCommand));
    }

    /**
     * Registers a proxy command with the plugin if a condition is met.
     *
     * @param bungeeCommand the proxy command to register
     * @param predicate     the condition that must be true to register the command
     */
    public void registerCommandIf(@NotNull final BungeeCommand bungeeCommand, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            super.getProxy().getPluginManager().registerCommand(this, new BungeeCommandExecutor(bungeeCommand));
        }
    }

    /**
     * Registers a listener with the plugin.
     *
     * @param listener the listener to register
     */
    public void registerListener(@NotNull final Listener listener) {
        ProxyServer.getInstance().getPluginManager().registerListener(this, listener);
    }

    /**
     * Registers a listener with the plugin if a condition is met.
     *
     * @param listener  the listener to register
     * @param predicate the condition that must be true to register the listener
     */
    public void registerListenerIf(@NotNull final Listener listener, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            ProxyServer.getInstance().getPluginManager().registerListener(this, listener);
        }
    }

    /**
     * Registers a module with the plugin.
     *
     * @param module the module to register
     */
    public void registerModule(@NotNull final PluginModule module) {
        this.moduleManager.registerModule(module);
    }

    /**
     * Registers a module with the plugin if a condition is met.
     *
     * @param module    the module to register
     * @param predicate the condition that must be true to register the module
     */
    public void registerModuleIf(@NotNull final PluginModule module, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.moduleManager.registerModule(module);
    }

    /**
     * Checks if the plugin has associated statistics.
     *
     * @return true if the plugin has statistics, false otherwise
     */
    public boolean hasStatistics() {
        return this.getClass().isAnnotationPresent(PluginStatisticProperties.class);
    }

    /**
     * Retrieves the statistics properties of the plugin.
     *
     * @return the PluginStatisticProperties annotation if present, null otherwise
     */
    public PluginStatisticProperties getStatisticProperties() {
        return this.getClass().getAnnotation(PluginStatisticProperties.class);
    }

    /**
     * Retrieves the identification annotation of the plugin.
     *
     * @return the PluginIdentification annotation if present, null otherwise
     */
    public PluginIdentification getPluginIdentification() {
        return this.getClass().getAnnotation(PluginIdentification.class);
    }

    /**
     * Checks if the plugin implements the DatabasePlugin interface.
     *
     * @return true if the plugin implements DatabasePlugin, false otherwise
     */
    public boolean hasDatabase() {
        return this.getClass().isAssignableFrom(DatabasePlugin.class);
    }

}
