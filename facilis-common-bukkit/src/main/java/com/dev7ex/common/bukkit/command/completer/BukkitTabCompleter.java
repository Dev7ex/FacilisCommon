package com.dev7ex.common.bukkit.command.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Dev7ex
 * @since 17.02.2024
 */
public interface BukkitTabCompleter extends TabCompleter {

    @Nullable
    @Override
    default List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final Command command, @NotNull final String commandLabel, @NotNull final String[] arguments) {
        return this.onTabComplete(commandSender, arguments);
    }

    List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final String[] arguments);

}
