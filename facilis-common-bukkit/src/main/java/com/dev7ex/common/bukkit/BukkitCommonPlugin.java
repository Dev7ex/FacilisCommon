package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;

import com.dev7ex.common.bukkit.plugin.PluginProperties;
import com.dev7ex.common.bukkit.plugin.service.PluginServiceOption;
import com.dev7ex.common.bukkit.plugin.service.PluginServiceOrder;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Dev7ex
 * @since 04.01.2022
 */
@Getter(AccessLevel.PUBLIC)
@PluginProperties(resourceId = 107198, metricsId = 17161, metrics = true)
public class BukkitCommonPlugin extends BukkitPlugin implements Listener {

    private BukkitCommonConfiguration configuration;
    private final UpdateChecker updateChecker = new UpdateChecker(this);

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

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void handlePluginEnable(final PluginEnableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }
        final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

        final PluginServiceOrder serviceOrder = plugin.getClass().getAnnotation(PluginServiceOrder.class);

        if (!plugin.getClass().isAnnotationPresent(PluginServiceOrder.class)) {
            plugin.registerCommands();
            plugin.registerListeners();
            plugin.registerServices();

        } else {
            Arrays.stream(serviceOrder.options()).forEach(option -> {
                switch (option) {
                    case COMMANDS:
                        plugin.registerCommands();
                        break;

                    case LISTENERS:
                        plugin.registerListeners();
                        break;

                    case SERVICES:
                        plugin.registerServices();
                }
            });
        }
        plugin.getServiceManager().onEnable();

        if (plugin.hasMetrics()) {
            plugin.enableMetrics();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void handlePluginDisable(final PluginDisableEvent event) {
        if (!(event.getPlugin() instanceof BukkitPlugin)) {
            return;
        }
        final BukkitPlugin plugin = (BukkitPlugin) event.getPlugin();

        plugin.getServiceManager().onDisable();
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
