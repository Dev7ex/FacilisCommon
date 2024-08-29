package com.dev7ex.common.bukkit.command.completer;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public interface MaterialTabCompleter extends BukkitTabCompleter {

    @Override
    default List<String> onTabComplete(final @NotNull CommandSender commandSender, final @NotNull String[] arguments) {
        return Arrays.stream(Material.values()).map(Enum::name).toList();
    }

}
