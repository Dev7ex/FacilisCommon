package com.dev7ex.common.bungeecord.plugin.service;

import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 12.07.2022
 */
public class ServiceManager {

    private final Map<String, PluginService> pluginServices = new HashMap<>();
    private final Plugin plugin;

    public ServiceManager(@NotNull final Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerService(@NotNull final PluginService pluginService) {
        this.pluginServices.put(pluginService.getName(), pluginService);
    }

    public void unregisterService(@NotNull final PluginService pluginService) {
        this.pluginServices.remove(pluginService.getName());
    }

    public void onEnable() {
        this.pluginServices.values().forEach(PluginService::onEnable);
    }

    public void onDisable() {
        this.pluginServices.values().forEach(PluginService::onDisable);
    }

}
