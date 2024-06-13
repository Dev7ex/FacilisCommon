package com.dev7ex.common.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Handles the execution of Bukkit commands.
 * This class checks for permissions and delegates the command execution to the corresponding {@link BukkitCommand}.
 *
 * @author Dev7ex
 * @since 13.07.2022
 */
public class BukkitCommandExecutor implements CommandExecutor {

    private final BukkitCommand bukkitCommand;

    /**
     * Constructs a new BukkitCommandExecutor for the specified BukkitCommand.
     *
     * @param bukkitCommand the command to be executed.
     */
    public BukkitCommandExecutor(@NotNull final BukkitCommand bukkitCommand) {
        this.bukkitCommand = bukkitCommand;
    }

    /**
     * Executes the given command, returning its success.
     * If false is returned, the "usage" string defined in the plugin.yml file will be sent to the player.
     *
     * @param commandSender the source of the command.
     * @param command       the command that was executed.
     * @param commandLabel  the alias of the command which was used.
     * @param arguments     the arguments passed to the command.
     * @return true if the command was successful, otherwise false.
     */
    @Override
    public boolean onCommand(@NotNull final CommandSender commandSender, @NotNull final Command command, @NotNull final String commandLabel, @NotNull final String[] arguments) {
        // Check if the command sender has the required permission
        if ((!this.bukkitCommand.getPermission().isBlank()) && (!commandSender.hasPermission(this.bukkitCommand.getPermission()))) {
            // Send a no permission message if the sender lacks the necessary permission
            commandSender.sendMessage(this.bukkitCommand.getConfiguration().getNoPermissionMessage());
            return true;
        }
        // Execute the command with the provided arguments
        this.bukkitCommand.execute(commandSender, arguments);
        return true;
    }

}