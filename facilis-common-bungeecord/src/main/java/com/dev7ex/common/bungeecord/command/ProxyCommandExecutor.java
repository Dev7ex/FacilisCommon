package com.dev7ex.common.bungeecord.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * Represents a BungeeCord command executor for a {@link ProxyCommand}.
 * This class handles command execution and tab completion for the associated ProxyCommand.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
public class ProxyCommandExecutor extends Command implements TabExecutor {

    private final ProxyCommand proxyCommand;

    /**
     * Constructs a new ProxyCommandExecutor for the given ProxyCommand.
     *
     * @param proxyCommand the ProxyCommand instance to execute
     */
    public ProxyCommandExecutor(@NotNull final ProxyCommand proxyCommand) {
        super(proxyCommand.getName(), proxyCommand.getPermission(), proxyCommand.getAliases());
        this.proxyCommand = proxyCommand;
    }

    /**
     * Executes the command.
     *
     * @param commandSender the sender of the command
     * @param arguments     the arguments passed to the command
     */
    @Override
    public void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        this.proxyCommand.execute(commandSender, arguments);
    }

    /**
     * Handles tab completion for the command.
     *
     * @param commandSender the sender requesting tab completion
     * @param arguments     the arguments passed to the command
     * @return a list of tab completions for the command
     */
    @Override
    public Iterable<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(this.proxyCommand instanceof TabExecutor)) {
            return Collections.emptyList();
        }
        return ((TabExecutor) this.proxyCommand).onTabComplete(commandSender, arguments);
    }

}
