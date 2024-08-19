package com.dev7ex.common.bungeecord.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * Represents a BungeeCord command executor for a {@link BungeeCommand}.
 * This class handles command execution and tab completion for the associated ProxyCommand.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
public class BungeeCommandExecutor extends Command implements TabExecutor {

    private final BungeeCommand bungeeCommand;

    /**
     * Constructs a new ProxyCommandExecutor for the given ProxyCommand.
     *
     * @param bungeeCommand the ProxyCommand instance to execute
     */
    public BungeeCommandExecutor(@NotNull final BungeeCommand bungeeCommand) {
        super(bungeeCommand.getName(), bungeeCommand.getPermission(), bungeeCommand.getAliases());
        this.bungeeCommand = bungeeCommand;
    }

    /**
     * Executes the command.
     *
     * @param commandSender the sender of the command
     * @param arguments     the arguments passed to the command
     */
    @Override
    public void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        this.bungeeCommand.execute(commandSender, arguments);
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
        if (!(this.bungeeCommand instanceof TabExecutor)) {
            return Collections.emptyList();
        }
        return ((TabExecutor) this.bungeeCommand).onTabComplete(commandSender, arguments);
    }

}
