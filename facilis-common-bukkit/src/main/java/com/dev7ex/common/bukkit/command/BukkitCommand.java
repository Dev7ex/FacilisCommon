package com.dev7ex.common.bukkit.command;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.ConfigurablePlugin;
import com.dev7ex.common.bukkit.plugin.configuration.BasePluginConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Abstract class for defining Bukkit commands.
 * This class provides basic functionalities for managing and executing main and subcommands.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
@Getter(AccessLevel.PUBLIC)
public abstract class BukkitCommand {

    /**
     * The plugin to which this command belongs.
     */
    private final BukkitPlugin plugin;

    /**
     * A map for managing subcommands.
     */
    private final Map<String, BukkitCommand> subCommands = new HashMap<>();

    /**
     * Constructor to initialize the command with the specified plugin.
     *
     * @param plugin the plugin to which this command belongs.
     */
    public BukkitCommand(@NotNull final BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the command with the given sender and arguments.
     *
     * @param commandSender the sender of the command.
     * @param arguments     the arguments provided with the command.
     */
    public abstract void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

    /**
     * Registers a subcommand to this command.
     *
     * @param subCommand the subcommand to be registered.
     */
    public void registerSubCommand(@NotNull final BukkitCommand subCommand) {
        if (subCommand.getAliases() != null && subCommand.getAliases().length > 0) {
            Arrays.stream(subCommand.getAliases())
                    .filter(alias -> !alias.isBlank())
                    .forEach(alias -> this.subCommands.put(alias, subCommand));
        }
        this.subCommands.put(subCommand.getName(), subCommand);
    }

    /**
     * Retrieves a subcommand by its name.
     *
     * @param name the name of the subcommand.
     * @return an Optional containing the subcommand if found, otherwise empty.
     */
    public Optional<BukkitCommand> getSubCommand(@NotNull final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    /**
     * Retrieves a subcommand by its class.
     *
     * @param commandClass the class of the subcommand.
     * @return the subcommand if found.
     * @throws NoSuchElementException if no subcommand is found with the specified class.
     */
    public @Nullable BukkitCommand getSubCommand(@NotNull final Class<? extends BukkitCommand> commandClass) {
        return this.subCommands.values().stream()
                .filter(subCommand -> subCommand.getClass() == commandClass)
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets the name of the command from its annotation.
     *
     * @return the name of the command.
     * @throws IllegalStateException if the annotation is missing.
     */
    public String getName() {
        final BukkitCommandProperties properties = this.getClass().getAnnotation(BukkitCommandProperties.class);
        if (properties == null) {
            throw new IllegalStateException("BukkitCommandProperties annotation is missing.");
        }
        return properties.name();
    }

    /**
     * Gets the permission required to execute the command from its annotation.
     *
     * @return the permission required for the command.
     * @throws IllegalStateException if the annotation is missing.
     */
    public String getPermission() {
        final BukkitCommandProperties properties = this.getClass().getAnnotation(BukkitCommandProperties.class);
        if (properties == null) {
            throw new IllegalStateException("BukkitCommandProperties annotation is missing.");
        }
        return properties.permission();
    }

    /**
     * Gets the aliases of the command from its annotation.
     *
     * @return an array of aliases for the command.
     * @throws IllegalStateException if the annotation is missing.
     */
    public String[] getAliases() {
        final BukkitCommandProperties properties = this.getClass().getAnnotation(BukkitCommandProperties.class);
        if (properties == null) {
            throw new IllegalStateException("BukkitCommandProperties annotation is missing.");
        }
        return properties.aliases();
    }

    /**
     * Gets the configuration for the plugin.
     *
     * @param <T> the type of the plugin configuration.
     * @return the configuration for the plugin.
     * @throws ClassCastException if the plugin does not implement ConfigurablePlugin.
     */
    @SuppressWarnings("unchecked")
    public <T extends BasePluginConfiguration> T getConfiguration() {
        if (!(this.plugin instanceof ConfigurablePlugin)) {
            throw new ClassCastException("Plugin does not implement ConfigurablePlugin.");
        }
        return (T) ((ConfigurablePlugin) this.plugin).getConfiguration();
    }
}