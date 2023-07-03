package com.dev7ex.common.bungeecord.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
public class ProxyCommandExecutor extends Command implements TabExecutor {

    private final ProxyCommand proxyCommand;

    public ProxyCommandExecutor(@NotNull final ProxyCommand proxyCommand) {
        super(proxyCommand.getName(), proxyCommand.getPermission(), proxyCommand.getAliases());
        this.proxyCommand = proxyCommand;
    }

    @Override
    public void execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        this.proxyCommand.execute(commandSender, arguments);
    }

    @Override
    public Iterable<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (!(this.proxyCommand instanceof TabExecutor)) {
            return Collections.emptyList();
        }
        return ((TabExecutor) this.proxyCommand).onTabComplete(commandSender, arguments);
    }

}
