package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.ConfigurablePlugin;
import com.dev7ex.common.bukkit.plugin.DatabasePlugin;
import com.dev7ex.common.bukkit.plugin.PluginIdentification;
import com.dev7ex.common.bukkit.plugin.statistic.PluginStatistic;
import com.dev7ex.common.bukkit.plugin.statistic.PluginStatisticProperties;
import com.dev7ex.common.bukkit.util.UpdateChecker;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginStatisticProperties(enabled = true, identification = 17161)
@PluginIdentification(spigotResourceId = 107198)
public class BukkitCommonPlugin extends BukkitPlugin implements Listener, ConfigurablePlugin {

    private BukkitCommonConfiguration configuration;
    private final UpdateChecker updateChecker = new UpdateChecker(this);
    private PluginStatistic statistic;

    @Override
    public void onLoad() {
        this.configuration = new BukkitCommonConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
        super.registerListener(this);
        this.updateChecker.getVersion((updateAvailable) -> {});
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void handlePluginEnable(final PluginEnableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }

        try {
            final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

            plugin.registerCommands();
            plugin.registerListeners();
            plugin.registerModules();

            if (plugin.hasStatistics()) {
                final PluginStatisticProperties statisticProperties = plugin.getStatisticProperties();

                if (!statisticProperties.enabled()) {
                    return;
                }
                this.statistic = new PluginStatistic(plugin, statisticProperties.identification());
            }

            if (super.hasDatabase()) {
                final DatabasePlugin databasePlugin = (DatabasePlugin) this;
                databasePlugin.onConnect();
            }
            plugin.getModuleManager().enableAllModules();

        } catch (final Exception exception) {
            super.getLogger().warning("This error was not triggered directly by FacilisCommon");
            super.getLogger().warning("If you are sure that this error comes from FacilisCommon then write an issue on Github");
            super.getLogger().info("https://github.com/Dev7ex/FacilisCommon/issues");
            exception.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void handlePluginDisable(final PluginDisableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }
        final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

        if (super.hasDatabase()) {
            final DatabasePlugin databasePlugin = (DatabasePlugin) plugin;
            databasePlugin.onDisconnect();
        }

        super.getModuleManager().disableAllModules();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!this.configuration.getBoolean("settings.receive-update-message")) {
            return;
        }

        if (!this.updateChecker.isUpdateAvailable()) {
            return;
        }
        player.sendMessage(this.configuration.getString("settings.update-message-player")
                .replaceAll("%prefix%", this.configuration.getPrefix()));
        player.sendMessage(this.configuration.getString("settings.update-message-version-player")
                .replaceAll("%prefix%", this.configuration.getPrefix())
                .replaceAll("%current_version%", super.getDescription().getVersion())
                .replaceAll("%new_version%", this.updateChecker.getNewVersion()));
    }

}
