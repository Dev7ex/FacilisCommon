package com.dev7ex.common.bukkit.plugin.module;

import com.dev7ex.common.bukkit.plugin.BasePlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 02.03.2024
 */
public class PluginModuleManager {

    private final BasePlugin plugin;
    private final Deque<PluginModule> moduleDeque = new ArrayDeque<>();
    private final Map<String, PluginModule> modules = new HashMap<>();

    public PluginModuleManager(@NotNull final BasePlugin plugin) {
        this.plugin = plugin;
    }

    public void registerModule(@NotNull final PluginModule module) {
        this.moduleDeque.add(module);
        this.modules.put(module.getName(), module);
    }

    public void unregisterModule(@NotNull final PluginModule module) {
        this.modules.remove(module.getName());
    }

    public void disableModule(@NotNull final PluginModule pluginModule) {
        pluginModule.onDisable();
    }

    public void disableModule(@NotNull final Class<? extends PluginModule> moduleClazz) {
        final PluginModule module = this.modules.values()
                .stream()
                .filter(pluginModule -> pluginModule.getClass() == moduleClazz)
                .findFirst()
                .orElseThrow();
        module.onDisable();
    }

    public void enableAllModules() {
        while (!this.moduleDeque.isEmpty()) {
            this.moduleDeque.pollFirst().onEnable();
        }
    }

    public void disableAllModules() {
        this.modules.values().forEach(PluginModule::onDisable);
        this.modules.clear();
    }

}
