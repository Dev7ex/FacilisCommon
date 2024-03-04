package com.dev7ex.common.bukkit.command.completer;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * @author Dev7ex
 * @since 17.02.2024
 */
public class EmptyTabCompleter implements BukkitTabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        return Collections.emptyList();
    }

}
