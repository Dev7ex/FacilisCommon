package com.dev7ex.common.bukkit.conversation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class BukkitConversationFlag {

    private boolean closeConversation = true;
    private boolean cancelMessage = true;

}
