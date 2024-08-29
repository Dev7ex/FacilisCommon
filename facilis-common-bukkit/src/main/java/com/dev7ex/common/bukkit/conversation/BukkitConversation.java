package com.dev7ex.common.bukkit.conversation;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public interface BukkitConversation {

    void begin(@NotNull final String message, @NotNull final BukkitConversationFlag flag);

}
