package com.dev7ex.common.bukkit.command;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.ConfigurablePlugin;
import com.dev7ex.common.bukkit.plugin.configuration.BasePluginConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    private String[] aliases = new String[]{};

    public BukkitCommand(@NotNull final BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

    public void registerSubCommand(@NotNull final BukkitCommand proxyCommand) {
        if((proxyCommand.getAliases()) != null && (proxyCommand.getAliases().length > 0)) {
            Arrays.stream(proxyCommand.getAliases()).filter(alias -> !alias.isBlank()).forEach(aliases -> this.subCommands.put(aliases, proxyCommand));
        }
        this.subCommands.put(proxyCommand.getName(), proxyCommand);
    }

    public Optional<BukkitCommand> getSubCommand(@NotNull final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    public @Nullable BukkitCommand getSubCommand(@NotNull final Class<? extends BukkitCommand> commandClazz) {
        return this.subCommands.values().stream().filter(subCommand -> subCommand.getClass() == commandClazz).findFirst().orElseThrow();
    }

    public String getName() {
        return this.getClass().getAnnotation(BukkitCommandProperties.class).name();
    }

    public String getPermission() {
        return this.getClass().getAnnotation(BukkitCommandProperties.class).permission();
    }

    public String[] getAliases() {
        return this.getClass().getAnnotation(BukkitCommandProperties.class).aliases();
    }

    public <T extends BasePluginConfiguration> T getConfiguration() {
        return ((ConfigurablePlugin) this.plugin).getConfiguration();
    }

}
