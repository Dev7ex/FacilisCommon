package com.dev7ex.common.bungeecord.command;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.bungeecord.plugin.ProxyPlugin;
import com.dev7ex.common.bungeecord.plugin.configuration.BasePluginConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents an abstract command in a BungeeCord proxy.
 * This class provides a structure for handling commands and subcommands.
 * Extend this class to implement custom commands.
 *
 * @param <T> the type of the plugin configuration
 * @version 2.0
 * @see ConfigurablePlugin
 * @see ProxyPlugin
 * @see BasePluginConfiguration
 * @since 19.07.2022
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class ProxyCommand {

    private final ProxyPlugin plugin;
    private final Map<String, ProxyCommand> subCommands = new HashMap<>();
    private String[] aliases = new String[]{};

    /**
     * Constructs a new ProxyCommand.
     *
     * @param plugin the plugin instance associated with this command
     */
    public ProxyCommand(@NotNull final ProxyPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the command.
     * This method must be implemented by subclasses to define the command's behavior.
     *
     * @param commandSender the sender of the command
     * @param arguments     the arguments passed to the command
     */
    public abstract void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

    /**
     * Registers a subcommand for this command.
     *
     * @param proxyCommand the subcommand to register
     */
    public void registerSubCommand(@NotNull final ProxyCommand proxyCommand) {
        if (proxyCommand.getAliases() != null && proxyCommand.getAliases().length > 0) {
            Arrays.stream(proxyCommand.getAliases())
                    .filter(alias -> !alias.isBlank())
                    .forEach(alias -> this.subCommands.put(alias, proxyCommand));
        }
        this.subCommands.put(proxyCommand.getName(), proxyCommand);
    }

    /**
     * Retrieves a subcommand by its name.
     *
     * @param name the name of the subcommand
     * @return an Optional containing the subcommand if found, or an empty Optional if not found
     */
    public Optional<ProxyCommand> getSubCommand(@NotNull final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    /**
     * Retrieves a subcommand by its class type.
     *
     * @param commandClazz the class of the subcommand
     * @return the subcommand instance if found, or throws an exception if not found
     */
    public @Nullable ProxyCommand getSubCommand(@NotNull final Class<? extends ProxyCommand> commandClazz) {
        return this.subCommands.values().stream()
                .filter(subCommand -> subCommand.getClass() == commandClazz)
                .findFirst()
                .orElseThrow();
    }

    /**
     * Gets the name of this command.
     *
     * @return the name of the command
     */
    public String getName() {
        return this.getClass().getAnnotation(ProxyCommandProperties.class).name();
    }

    /**
     * Gets the permission required to execute this command.
     *
     * @return the permission string
     */
    public String getPermission() {
        return this.getClass().getAnnotation(ProxyCommandProperties.class).permission();
    }

    /**
     * Gets the aliases for this command.
     *
     * @return an array of aliases
     */
    public String[] getAliases() {
        return this.getClass().getAnnotation(ProxyCommandProperties.class).aliases();
    }

    /**
     * Gets the configuration associated with the plugin.
     *
     * @return the plugin configuration
     */
    public <T extends BasePluginConfiguration> T getConfiguration() {
        return ((ConfigurablePlugin) this.plugin).getConfiguration();
    }

}
