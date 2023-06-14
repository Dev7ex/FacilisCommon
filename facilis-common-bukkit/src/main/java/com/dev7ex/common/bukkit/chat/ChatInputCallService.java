package com.dev7ex.common.bukkit.chat;

import com.dev7ex.common.bukkit.plugin.service.PluginService;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dev7ex
 * @since 27.02.2023
 */
public class ChatInputCallService implements PluginService {

    private final Map<UUID, ChatInputCall> chatInputCalls = new HashMap<>();
    private final Lock reentrantLock = new ReentrantLock();

    public ChatInputCallService(@NotNull final Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new ChatInputCallListener(this), plugin);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        this.chatInputCalls.clear();
    }

    public ChatInputCall getChatInputCall(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();

        try {
            return this.chatInputCalls.get(uniqueId);

        } finally {
            this.reentrantLock.unlock();
        }

    }

    public void addChatCall(@NotNull final UUID uniqueId, @NotNull final ChatInputCall chatInputCall) {
        this.reentrantLock.lock();
        try {
            this.chatInputCalls.put(uniqueId, chatInputCall);

        } finally {
            this.reentrantLock.unlock();
        }
    }

    public void removeChatCall(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();
        try {
            this.chatInputCalls.remove(uniqueId);

        } finally {
            this.reentrantLock.unlock();
        }
    }

}
