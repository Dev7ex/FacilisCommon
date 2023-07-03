package com.dev7ex.common.bungeecord.command;

import com.dev7ex.common.bungeecord.plugin.ProxyPlugin;
import com.dev7ex.common.bungeecord.plugin.configuration.BasePluginConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
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
@Setter(AccessLevel.PUBLIC)
public abstract class ProxyCommand {

    private final ProxyPlugin plugin;
    private final Map<String, ProxyCommand> subCommands = new HashMap<>();
    private String[] aliases = new String[]{};

    public ProxyCommand(@NotNull final ProxyPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

    public void execute(@NotNull final ProxyCommand proxyCommand, @NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if ((!this.getPermission().isBlank()) && (!commandSender.hasPermission(this.getPermission()))) {
            commandSender.sendMessage(new TextComponent(this.plugin.getConfiguration().getNoPermissionMessage()));
            return;
        }
         proxyCommand.execute(commandSender, arguments);
    }

    public void registerSubCommand(@NotNull final ProxyCommand proxyCommand) {
        if((proxyCommand.getAliases()) != null && (proxyCommand.getAliases().length == 0)) {
            Arrays.stream(proxyCommand.getAliases()).forEach(aliases -> this.subCommands.put(aliases, proxyCommand));
        }
        this.subCommands.put(proxyCommand.getName(), proxyCommand);
    }

    public Optional<ProxyCommand> getSubCommand(@NotNull final String name) {
        return Optional.ofNullable(this.subCommands.get(name));
    }

    public String getName() {
        return this.getClass().getAnnotation(CommandProperties.class).name();
    }

    public String getPermission() {
        return this.getClass().getAnnotation(CommandProperties.class).permission();
    }

    public String[] getAliases() {
        return this.getClass().getAnnotation(CommandProperties.class).aliases();
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
