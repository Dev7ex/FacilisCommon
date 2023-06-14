package com.dev7ex.common.bukkit.plugin.service;

import org.bukkit.plugin.Plugin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 12.07.2022
 */
public class ServiceManager {

    private final Deque<PluginService> serviceDeque = new ArrayDeque<>();
    private final Map<String, PluginService> pluginServices = new HashMap<>();
    private final Plugin plugin;

    public ServiceManager(final Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerService(final PluginService pluginService) {
        this.serviceDeque.add(pluginService);
        this.pluginServices.put(pluginService.getName(), pluginService);
    }

    public void unregisterService(final PluginService pluginService) {
        this.pluginServices.remove(pluginService.getName());
    }

    public void onEnable() {
        while (!this.serviceDeque.isEmpty()) {
            this.serviceDeque.pollFirst().onEnable();
        }
    }

    public void onDisable() {
        this.pluginServices.values().forEach(PluginService::onDisable);
    }

}
