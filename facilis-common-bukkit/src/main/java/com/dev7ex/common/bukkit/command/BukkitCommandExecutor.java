package com.dev7ex.common.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Dev7ex
 * @since 13.07.2022
 */
public class BukkitCommandExecutor implements CommandExecutor {

    private final BukkitCommand bukkitCommand;

    public BukkitCommandExecutor(final BukkitCommand bukkitCommand) {
        this.bukkitCommand = bukkitCommand;
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String commandLabel, final String[] arguments) {
        if ((!this.bukkitCommand.getPermission().isBlank()) && (!commandSender.hasPermission(this.bukkitCommand.getPermission()))) {
            commandSender.sendMessage(this.bukkitCommand.getNoPermissionMessage());
            return true;
        }
        return this.bukkitCommand.execute(commandSender, arguments);
    }

}
