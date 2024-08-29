package com.dev7ex.common.bukkit.conversation;

import com.dev7ex.common.bukkit.plugin.module.PluginModule;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public class BukkitConversationModule implements PluginModule {

    private final Map<UUID, BukkitConversation> conversations = new HashMap<>();
    private final Lock reentrantLock = new ReentrantLock();

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        this.conversations.clear();
    }

    public void registerConversation(@NotNull final UUID uniqueId, @NotNull final BukkitConversation conversation) {
        this.reentrantLock.lock();
        try {
            this.conversations.put(uniqueId, conversation);

        } finally {
            this.reentrantLock.unlock();
        }
    }

    public void unregisterConversation(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();
        try {
            this.conversations.remove(uniqueId);

        } finally {
            this.reentrantLock.unlock();
        }
    }

    public Optional<BukkitConversation> getConversation(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();
        try {
            return Optional.ofNullable(this.conversations.get(uniqueId));

        } finally {
            this.reentrantLock.unlock();
        }
    }

}
