package com.dev7ex.common.bukkit.chat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dev7ex
 * @since 27.02.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChatInputRequestOption {

    private boolean remove = true;
    private boolean cancel = true;

}
