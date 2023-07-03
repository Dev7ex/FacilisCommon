package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.command.ProxyCommand;
import com.dev7ex.common.bungeecord.command.ProxyCommandExecutor;
import com.dev7ex.common.bungeecord.plugin.service.PluginService;
import com.dev7ex.common.bungeecord.plugin.service.ServiceManager;
import com.dev7ex.common.bungeecord.plugin.metrics.Metrics;
import lombok.AccessLevel;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.function.Predicate;

@Getter(AccessLevel.PUBLIC)
public class PluginBase extends Plugin {

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

    public void createSubFolder(@NotNull final String folderName) {
        final File subFolder = new File(this.getDataFolder().getPath() + File.separator + folderName);
        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }
    }

    public void enableMetrics() {
        this.metrics = new Metrics(this, this.getMetricsId());
    }

    public File getSubFolder(@NotNull final String folderName) {
        return new File(this.getDataFolder().getPath() + File.separator + folderName);
    }

    protected void registerCommand(@NotNull final Command command) {
        super.getProxy().getPluginManager().registerCommand(this, command);
    }

    protected void registerCommandIf(@NotNull final Command command, final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        super.getProxy().getPluginManager().registerCommand(this, command);
    }

    protected void registerCommand(@NotNull final ProxyCommand proxyCommand) {
        super.getProxy().getPluginManager().registerCommand(this, new ProxyCommandExecutor(proxyCommand));
    }

    protected void registerCommandIf(@NotNull final ProxyCommand proxyCommand, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            super.getProxy().getPluginManager().registerCommand(this, new ProxyCommandExecutor(proxyCommand));
        }
    }

    public void registerListener(@NotNull final Listener listener) {
        super.getProxy().getPluginManager().registerListener(this, listener);
    }

    public void registerListenerIf(@NotNull final Listener listener, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            super.getProxy().getPluginManager().registerListener(this, listener);
        }
    }

    public void registerService(@NotNull final PluginService pluginService) {
        this.serviceManager.registerService(pluginService);
    }

    public void registerServiceIf(@NotNull final PluginService pluginService, @NotNull final Predicate<Boolean> predicate) {
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
        if (!this.getClass().isAnnotationPresent(PluginProperties.class)) {
            return false;
        }
        return this.getClass().getAnnotation(PluginProperties.class).metrics();
    }

}
