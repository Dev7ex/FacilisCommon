package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.plugin.configuration.BasePluginConfiguration;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
public abstract class ProxyPlugin extends PluginBase {

    @Override
    public void onEnable() {
        this.registerServices();

        super.getServiceManager().onEnable();

        this.registerCommands();
        this.registerListeners();

        if (super.hasMetrics()) {
            super.enableMetrics();
        }
    }

    @Override
    public void onDisable() {
        super.getServiceManager().onDisable();
    }

    public void registerCommands() {}

    public void registerListeners() {}

    public void registerServices() {}

    public abstract <T extends BasePluginConfiguration> T getConfiguration();

    public static <T extends Plugin> T getPlugin(@NotNull final Class<? extends Plugin> clazz) {
        final Optional<Plugin> plugin = ProxyServer.getInstance().getPluginManager().getPlugins()
                .stream()
                .filter(searchedPlugin -> searchedPlugin.getClass() == clazz)
                .findFirst();
        return (T) plugin.orElseThrow();
    }

}
