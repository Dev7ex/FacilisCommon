package com.dev7ex.common.bukkit.chat;

import com.dev7ex.common.bukkit.plugin.module.PluginModule;
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
public class ChatInputRequestService implements PluginModule {

    private final Map<UUID, ChatInputRequest> chatInputRequests = new HashMap<>();
    private final Lock reentrantLock = new ReentrantLock();

    public ChatInputRequestService(@NotNull final Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new ChatInputRequestListener(this), plugin);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        this.chatInputRequests.clear();
    }

    public ChatInputRequest getChatInputRequest(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();

        try {
            return this.chatInputRequests.get(uniqueId);

        } finally {
            this.reentrantLock.unlock();
        }

    }

    public void addChatRequest(@NotNull final UUID uniqueId, @NotNull final ChatInputRequest chatInputRequest) {
        this.reentrantLock.lock();
        try {
            this.chatInputRequests.put(uniqueId, chatInputRequest);

        } finally {
            this.reentrantLock.unlock();
        }
    }

    public void removeChatRequest(@NotNull final UUID uniqueId) {
        this.reentrantLock.lock();
        try {
            this.chatInputRequests.remove(uniqueId);

        } finally {
            this.reentrantLock.unlock();
        }
    }

}
