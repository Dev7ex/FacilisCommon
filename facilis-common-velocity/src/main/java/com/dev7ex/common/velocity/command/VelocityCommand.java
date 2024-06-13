package com.dev7ex.common.velocity.command;

import com.dev7ex.common.velocity.plugin.ConfigurablePlugin;
import com.dev7ex.common.velocity.plugin.VelocityPlugin;
import com.dev7ex.common.velocity.plugin.configuration.BasePluginConfiguration;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import lombok.AccessLevel;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Dev7ex
 * @since 09.06.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class VelocityCommand implements SimpleCommand {

    private final VelocityPlugin plugin;
    private final Map<String, VelocityCommand> subCommands = new HashMap<>();

    public VelocityCommand(@NotNull final VelocityPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final Invocation invocation) {
        if ((!this.getPermission().isBlank()) && (!invocation.source().hasPermission(this.getPermission()))) {
            invocation.source().sendMessage(MiniMessage.miniMessage().deserialize(this.getConfiguration().getNoPermissionMessage()));
            return;
        }
        this.execute(invocation.source(), invocation.arguments());
    }

    public abstract void execute(@NotNull final CommandSource commandSource, @NotNull final String[] arguments);

    public void registerSubCommand(@NotNull final VelocityCommand proxyCommand) {
        if ((proxyCommand.getAliases()) != null && (proxyCommand.getAliases().length > 0)) {
            Arrays.stream(proxyCommand.getAliases()).filter(alias -> !alias.isBlank()).forEach(aliases -> this.subCommands.put(aliases, proxyCommand));
        }
        this.subCommands.put(proxyCommand.getName(), proxyCommand);
    }

    public Optional<VelocityCommand> getSubCommand(@NotNull final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    public @Nullable VelocityCommand getSubCommand(@NotNull final Class<? extends VelocityCommand> commandClazz) {
        return this.subCommands.values().stream().filter(subCommand -> subCommand.getClass() == commandClazz).findFirst().orElseThrow();
    }

    public @NotNull MiniMessage getMiniMessage() {
        return MiniMessage.miniMessage();
    }

    public String getName() {
        return this.getClass().getAnnotation(VelocityCommandProperties.class).name();
    }

    public String getPermission() {
        return this.getClass().getAnnotation(VelocityCommandProperties.class).permission();
    }

    public String[] getAliases() {
        return this.getClass().getAnnotation(VelocityCommandProperties.class).aliases();
    }

    public <T extends BasePluginConfiguration> T getConfiguration() {
        return ((ConfigurablePlugin) this.plugin).getConfiguration();
    }

}
