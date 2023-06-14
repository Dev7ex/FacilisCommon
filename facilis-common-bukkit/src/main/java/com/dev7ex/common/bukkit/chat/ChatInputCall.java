package com.dev7ex.common.bukkit.chat;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 27.02.2023
 */
public interface ChatInputCall {

    void call(@NotNull final String message, @NotNull final ChatInputCallOption options);

}
