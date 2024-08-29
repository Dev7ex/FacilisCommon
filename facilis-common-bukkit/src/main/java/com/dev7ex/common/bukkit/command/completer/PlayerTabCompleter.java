package com.dev7ex.common.bukkit.command.completer;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public interface PlayerTabCompleter extends BukkitTabCompleter {

    @Override
    default List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList();
    }

}
