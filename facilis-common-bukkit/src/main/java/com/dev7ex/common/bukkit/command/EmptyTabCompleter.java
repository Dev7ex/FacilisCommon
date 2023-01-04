package com.dev7ex.common.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

/**
 * @author Dev7ex
 * @since 12.12.2022
 */
public interface EmptyTabCompleter extends TabCompleter {

    @Override
    default List<String> onTabComplete(final CommandSender commandSender, final Command command, final String commandLabel, final String[] arguments) {
        return Collections.emptyList();
    }

}
