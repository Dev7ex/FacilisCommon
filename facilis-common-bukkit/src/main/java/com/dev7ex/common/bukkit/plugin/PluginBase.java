package com.dev7ex.common.bukkit.plugin;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.BukkitCommandExecutor;
import com.dev7ex.common.bukkit.plugin.metrics.Metrics;
import com.dev7ex.common.bukkit.plugin.service.PluginService;
import com.dev7ex.common.bukkit.plugin.service.ServiceManager;

import lombok.AccessLevel;
import lombok.Getter;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.function.Predicate;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
@Getter(AccessLevel.PUBLIC)
public class PluginBase extends JavaPlugin {

    private Metrics metrics;

    /**
     * ServiceManager represents a container for all PluginServices
     */
    private final ServiceManager serviceManager = new ServiceManager(this);

    public void createDataFolder() {
        if (super.getDataFolder().exists()) {
            return;
        }
        super.getDataFolder().mkdirs();
    }

    public void createSubFolder(final String folderName) {
        final File subFolder = new File(this.getDataFolder().getPath() + File.separator + folderName);
        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }
    }

    public void enableMetrics() {
        this.metrics = new Metrics(this, this.getMetricsId());
    }

    public File getSubFolder(final String folderName) {
        return new File(this.getDataFolder().getPath() + File.separator + folderName);
    }

    public PluginCommand registerCommand(final String command) {
        return super.getServer().getPluginCommand(command);
    }

    public void registerCommand(final BukkitCommand bukkitCommand) {
        final PluginCommand pluginCommand = super.getCommand(bukkitCommand.getName());
        pluginCommand.setExecutor(new BukkitCommandExecutor(bukkitCommand));

        if (!(bukkitCommand instanceof TabCompleter)) {
            return;
        }
        pluginCommand.setTabCompleter((TabCompleter) bukkitCommand);
    }

    public void registerListener(final Listener listener) {
        super.getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerListenerIf(final Listener listener, final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            super.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void registerService(final PluginService pluginService) {
        this.serviceManager.registerService(pluginService);
    }

    public void registerServiceIf(final PluginService pluginService, final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.serviceManager.registerService(pluginService);
    }

    public int getResourceId() {
        return this.getClass().getAnnotation(PluginProperties.class).resourceId();
    }

    public int getMetricsId() {
        return this.getClass().getAnnotation(PluginProperties.class).metricsId();
    }

    public boolean hasMetrics() {
        return this.getClass().getAnnotation(PluginProperties.class).metrics();
    }

}
