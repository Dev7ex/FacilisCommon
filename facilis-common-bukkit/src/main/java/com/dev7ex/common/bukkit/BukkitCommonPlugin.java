package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.ConfigurablePlugin;
import com.dev7ex.common.bukkit.plugin.DatabasePlugin;
import com.dev7ex.common.bukkit.plugin.PluginIdentification;
import com.dev7ex.common.bukkit.plugin.statistic.PluginStatistic;
import com.dev7ex.common.bukkit.plugin.statistic.PluginStatisticProperties;
import lombok.AccessLevel;
import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginStatisticProperties(enabled = true, identification = 17161)
@PluginIdentification(spigotResourceId = 107198)
public class BukkitCommonPlugin extends BukkitPlugin implements Listener, ConfigurablePlugin {

    private BukkitCommonConfiguration configuration;

    @Override
    public void onLoad() {
        this.configuration = new BukkitCommonConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
        super.registerListener(this);

        super.getLogger().info("Server Version: " + super.getProtocolVersion().getName());
        super.getLogger().info("Server Software: " + BukkitCommon.getServerSoftware());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePluginEnable(final PluginEnableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }

        try {
            final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

            plugin.registerManagers();
            plugin.registerModules();
            plugin.registerCommands();
            plugin.registerListeners();
            plugin.registerTasks();

            if (plugin.hasStatistics()) {
                final PluginStatisticProperties statisticProperties = plugin.getStatisticProperties();

                if (!statisticProperties.enabled()) {
                    return;
                }
                plugin.setStatistic(new PluginStatistic(plugin, statisticProperties.identification()));
            }

            if (plugin.getAudiences() == null) {
                plugin.setAudiences(BukkitAudiences.create(plugin));
            }

            if (plugin.hasDatabase()) {
                final DatabasePlugin databasePlugin = (DatabasePlugin) plugin;
                databasePlugin.onConnect();
            }
            plugin.getModuleManager().enableAllModules();

        } catch (final Exception exception) {
            super.getLogger().warning(" ");
            super.getLogger().warning("FacilisCommon has detected an error in the plugin " + event.getPlugin().getName());
            super.getLogger().warning("If you are sure that this error comes from FacilisCommon then write an issue on Github");
            super.getLogger().warning("https://github.com/Dev7ex/FacilisCommon/issues");
            super.getLogger().warning(" ");
            exception.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePluginDisable(final PluginDisableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }
        final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

        if (plugin.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) plugin;
            databasePlugin.onDisconnect();
        }
        plugin.getModuleManager().disableAllModules();
    }

    public static BukkitCommonPlugin getInstance() {
        return JavaPlugin.getPlugin(BukkitCommonPlugin.class);
    }

}
