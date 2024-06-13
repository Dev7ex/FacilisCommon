package com.dev7ex.common.bungeecord.plugin.module;

import com.dev7ex.common.bungeecord.plugin.BasePlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the lifecycle of plugin modules for a BungeeCord plugin.
 * Modules can be registered, enabled, disabled, and unregistered.
 * Provides methods to manage modules individually or in bulk.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
public class PluginModuleManager {

    private final BasePlugin plugin;
    private final Deque<PluginModule> moduleDeque = new ArrayDeque<>();
    private final Map<String, PluginModule> modules = new HashMap<>();

    /**
     * Constructs a module manager for the given plugin.
     *
     * @param plugin the BungeeCord plugin instance
     */
    public PluginModuleManager(@NotNull final BasePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers a module with the manager.
     * The module will be added to the module deque and mapped by its name.
     *
     * @param module the module to register
     */
    public void registerModule(@NotNull final PluginModule module) {
        this.moduleDeque.add(module);
        this.modules.put(module.getName(), module);
    }

    /**
     * Unregisters a module from the manager.
     * The module will be removed from the module map.
     *
     * @param module the module to unregister
     */
    public void unregisterModule(@NotNull final PluginModule module) {
        this.modules.remove(module.getName());
    }

    /**
     * Disables a specific module by invoking its {@link PluginModule#onDisable()} method.
     *
     * @param pluginModule the module to disable
     */
    public void disableModule(@NotNull final PluginModule pluginModule) {
        pluginModule.onDisable();
    }

    /**
     * Disables a module of a specific class by finding it in the registered modules.
     *
     * @param moduleClazz the class of the module to disable
     * @throws IllegalArgumentException if no module of the specified class is found
     */
    public void disableModule(@NotNull final Class<? extends PluginModule> moduleClazz) {
        final PluginModule module = this.modules.values()
                .stream()
                .filter(pluginModule -> pluginModule.getClass() == moduleClazz)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Module not found: " + moduleClazz.getName()));
        module.onDisable();
    }

    /**
     * Enables all registered modules by invoking their {@link PluginModule#onEnable()} methods.
     */
    public void enableAllModules() {
        while (!this.moduleDeque.isEmpty()) {
            this.moduleDeque.pollFirst().onEnable();
        }
    }

    /**
     * Disables all registered modules by invoking their {@link PluginModule#onDisable()} methods.
     */
    public void disableAllModules() {
        this.modules.values().forEach(PluginModule::onDisable);
        this.modules.clear();
    }

}
