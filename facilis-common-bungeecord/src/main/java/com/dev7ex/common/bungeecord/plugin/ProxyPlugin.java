package com.dev7ex.common.bungeecord.plugin;

import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatistic;
import com.dev7ex.common.bungeecord.plugin.statistic.PluginStatisticProperties;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
public abstract class ProxyPlugin extends BasePlugin {

    private PluginStatistic statistic;

    @Override
    public void onEnable() {
        this.registerCommands();
        this.registerListeners();
        this.registerModules();

        if (super.hasStatistics()) {
            final PluginStatisticProperties statisticProperties = super.getStatisticProperties();

            if (!statisticProperties.enabled()) {
                return;
            }
            this.statistic = new PluginStatistic(this, statisticProperties.identification());
        }

        if (super.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) this;
            databasePlugin.onConnect();
        }
        super.getModuleManager().enableAllModules();
    }

    @Override
    public void onDisable() {
        super.getModuleManager().disableAllModules();

        if (super.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) this;
            databasePlugin.onDisconnect();
        }
    }

    public static <T extends Plugin> T getPlugin(@NotNull final Class<? extends Plugin> clazz) {
        final Optional<Plugin> plugin = ProxyServer.getInstance().getPluginManager().getPlugins()
                .stream()
                .filter(searchedPlugin -> searchedPlugin.getClass() == clazz)
                .findFirst();
        return (T) plugin.orElseThrow();
    }

}
