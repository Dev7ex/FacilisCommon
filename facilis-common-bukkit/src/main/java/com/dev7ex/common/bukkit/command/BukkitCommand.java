package com.dev7ex.common.bukkit.command;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.configuration.BasePluginConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
@Getter(AccessLevel.PUBLIC)
public abstract class BukkitCommand {

    private final BukkitPlugin plugin;
    private final Map<String, BukkitCommand> subCommands = new HashMap<>();

    public BukkitCommand(@NotNull final BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

    @Deprecated
    public boolean execute(final BukkitCommand bukkitCommand, final CommandSender commandSender, final String[] arguments) {
        if ((!this.getPermission().isBlank()) && (!commandSender.hasPermission(this.getPermission()))) {
            commandSender.sendMessage(this.plugin.getConfiguration().getNoPermissionMessage());
            return true;
        }
        return bukkitCommand.execute(commandSender, arguments);
    }

    public void registerSubCommand(final BukkitCommand bukkitCommand) {
        if((bukkitCommand.getAliases()) != null && (bukkitCommand.getAliases().length == 0)) {
            Arrays.stream(bukkitCommand.getAliases()).forEach(aliases -> this.subCommands.put(aliases, bukkitCommand));
        }
        this.subCommands.put(bukkitCommand.getName(), bukkitCommand);
    }

    public Optional<BukkitCommand> getSubCommand(final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    public String getName() {
        return this.getClass().getAnnotation(CommandProperties.class).name();
    }

    public String[] getAliases() {
        return this.getClass().getAnnotation(CommandProperties.class).aliases();
    }

    public String getPermission() {
        return this.getClass().getAnnotation(CommandProperties.class).permission();
    }

    public <T extends BasePluginConfiguration> T getConfiguration() {
        return this.plugin.getConfiguration();
    }

    public String getPrefix() {
        return this.plugin.getConfiguration().getPrefix();
    }

    public String getNoPermissionMessage() {
        return this.plugin.getConfiguration().getNoPermissionMessage();
    }

}
