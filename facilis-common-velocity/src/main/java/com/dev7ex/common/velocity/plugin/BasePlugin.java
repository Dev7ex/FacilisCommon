package com.dev7ex.common.velocity.plugin;

import com.dev7ex.common.velocity.command.VelocityCommand;
import com.dev7ex.common.velocity.event.plugin.VelocityPluginDisableEvent;
import com.dev7ex.common.velocity.event.plugin.VelocityPluginEnableEvent;
import com.dev7ex.common.velocity.event.plugin.VelocityPluginLoadEvent;
import com.dev7ex.common.velocity.plugin.module.PluginModule;
import com.dev7ex.common.velocity.plugin.module.PluginModuleManager;
import com.dev7ex.common.velocity.plugin.statistic.PluginStatistic;
import com.dev7ex.common.velocity.plugin.statistic.PluginStatisticProperties;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AccessLevel;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.function.Predicate;

/**
 * Represents a base class for Velocity plugins.
 * This class provides essential functionality for plugins, such as managing the plugin's modules,
 * accessing the Velocity server, logger, and data directory, and handling data folder operations.
 * <p>
 * Subclasses should implement the abstract methods {@link #onLoad()}, {@link #onEnable()}, and {@link #onDisable()}
 * to define custom behaviors during the plugin's lifecycle events.
 * Additionally, methods for registering commands, listeners, modules, and tasks can be overridden as needed.
 * <p>
 * This class also supports plugin statistics if annotated with {@link PluginStatisticProperties}.
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class BasePlugin {

    private final PluginModuleManager moduleManager = new PluginModuleManager(this);
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;
    private PluginStatistic statistic;

    /**
     * Constructs a new BasePlugin object with the specified parameters.
     *
     * @param server        The ProxyServer object representing the Velocity server.
     * @param logger        The Logger object for logging messages.
     * @param dataDirectory The path to the data directory.
     */
    public BasePlugin(@NotNull final ProxyServer server, @NotNull final Logger logger, @DataDirectory final Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    /**
     * Initializes the plugin when the proxy server starts.
     *
     * @param event The ProxyInitializeEvent object representing the initialization event.
     */
    @Subscribe
    public void onInitialize(final ProxyInitializeEvent event) {
        this.onLoad();
        this.getServer().getEventManager().fire(new VelocityPluginLoadEvent(this));

        this.registerManagers();
        this.registerModules();
        this.registerTasks();
        this.registerCommands();
        this.registerListeners();

        if (this.hasStatistics()) {
            final PluginStatisticProperties statisticProperties = this.getStatisticProperties();

            if (!statisticProperties.enabled()) {
                return;
            }
            this.statistic = new PluginStatistic(this, this.server, this.logger, this.dataDirectory, statisticProperties.identification());
        }
        this.moduleManager.enableAllModules();
        this.onEnable();
        this.getServer().getEventManager().fire(new VelocityPluginEnableEvent(this));
    }

    /**
     * Disable the plugin when the proxy server stops.
     *
     * @param event The ProxyShutdownEvent object representing the shutdown event.
     */
    @Subscribe
    public void onShutdown(final ProxyShutdownEvent event) {
        this.moduleManager.disableAllModules();
        this.onDisable();
        this.getServer().getEventManager().fire(new VelocityPluginDisableEvent(this));
    }

    /**
     * Method to define custom behavior when the plugin is loaded.
     * Subclasses should override this method to implement custom load logic.
     */
    public abstract void onLoad();

    /**
     * Method to define custom behavior when the plugin is enabled.
     * Subclasses should override this method to implement custom enable logic.
     */
    public abstract void onEnable();

    /**
     * Method to define custom behavior when the plugin is disabled.
     * Subclasses should override this method to implement custom disable logic.
     */
    public abstract void onDisable();

    /**
     * Registers commands for the plugin.
     * Override this method to register commands specific to the plugin.
     */
    public void registerCommands() {
    }

    /**
     * Registers listeners for the plugin.
     * Override this method to register listeners specific to the plugin.
     */
    public void registerListeners() {
    }

    public void registerManagers() {

    }

    /**
     * Registers modules for the plugin.
     * Override this method to register modules specific to the plugin.
     */
    public void registerModules() {
    }

    /**
     * Registers tasks for the plugin.
     * Override this method to register tasks specific to the plugin.
     */
    public void registerTasks() {
    }

    /**
     * Creates the data folder for the plugin if it does not exist.
     * This folder is used to store configuration files, data, or any other persistent information.
     * If the folder already exists, this method does nothing.
     */
    public void createDataFolder() {
        if (this.dataDirectory.toFile().exists()) {
            return;
        }
        this.dataDirectory.toFile().mkdirs();
    }

    /**
     * Retrieves the data folder for the plugin.
     *
     * @return The data folder.
     */
    public File getDataFolder() {
        return this.dataDirectory.toFile();
    }

    /**
     * Creates a subfolder within the data folder if it does not exist.
     *
     * @param folderName The name of the subfolder to create.
     */
    public void createSubFolder(@NotNull final String folderName) {
        final File subFolder = new File(this.dataDirectory.toFile().getPath() + File.separator + folderName);
        if (subFolder.exists()) {
            return;
        }
        subFolder.mkdirs();
    }

    /**
     * Retrieves a subfolder within the data folder.
     *
     * @param folderName The name of the subfolder to retrieve.
     * @return The subfolder, or null if it does not exist.
     */
    public File getSubFolder(@NotNull final String folderName) {
        return new File(this.dataDirectory.toFile().getPath() + File.separator + folderName);
    }

    public void registerCommand(@NotNull final VelocityCommand command) {
        final CommandMeta commandMeta = this.server.getCommandManager().metaBuilder(command.getName())
                .aliases(command.getAliases())
                .plugin(this)
                .build();
        this.server.getCommandManager().register(commandMeta, command);
    }

    public void registerCommandIf(@NotNull final VelocityCommand command, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.registerCommand(command);
    }

    public void saveResource(@NotNull final String resourcePath, final boolean replace) {
        if (resourcePath.isEmpty()) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }
        final File file = new File(this.dataDirectory.toFile(), resourcePath.replace('/', File.separatorChar));

        try (final InputStream inputStream = this.getResource(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found");
            }
            if ((file.exists()) && (!replace)) {
                this.logger.warn("Could not save " + file.getName() + " to " + resourcePath + " because " + file.getName() + " already exists");
                return;
            }
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (final IOException exception) {
            this.logger.error("Could not save " + file.getName());
        }
    }

    public InputStream getResource(@NotNull final String resourcePath) {
        try {
            final URL url = this.getClass().getResource(resourcePath);
            if (url == null) {
                return null;
            }
            final URLConnection connection = url.openConnection();
            connection.setUseCaches(true);
            return connection.getInputStream();

        } catch (final IOException exception) {
            return null;
        }
    }

    /**
     * Registers a listener for the plugin.
     *
     * @param listener The listener object to register.
     */
    public void registerListener(@NotNull final Object listener) {
        this.server.getEventManager().register(this, listener);
    }

    /**
     * Registers a listener for the plugin if the specified predicate is true.
     *
     * @param listener  The listener object to register.
     * @param predicate The predicate to evaluate.
     */
    public void registerListenerIf(@NotNull final Object listener, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.server.getEventManager().register(this, listener);
    }

    /**
     * Registers a module for the plugin.
     *
     * @param module The module object to register.
     */
    public void registerModule(@NotNull final PluginModule module) {
        this.moduleManager.registerModule(module);
    }

    /**
     * Registers a module for the plugin if the specified predicate is true.
     *
     * @param module    The module object to register.
     * @param predicate The predicate to evaluate.
     */
    public void registerModuleIf(@NotNull final PluginModule module, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.moduleManager.registerModule(module);
    }

    /**
     * Checks if the plugin has statistics enabled.
     *
     * @return true if the plugin has statistics enabled, false otherwise.
     */
    public boolean hasStatistics() {
        return this.getClass().isAnnotationPresent(PluginStatisticProperties.class);
    }

    /**
     * Retrieves the properties of the plugin's statistics.
     *
     * @return The PluginStatisticProperties object representing the properties of the plugin's statistics.
     */
    public PluginStatisticProperties getStatisticProperties() {
        return this.getClass().getAnnotation(PluginStatisticProperties.class);
    }
}
