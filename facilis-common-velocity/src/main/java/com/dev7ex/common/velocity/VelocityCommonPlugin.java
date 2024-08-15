package com.dev7ex.common.velocity;

import com.dev7ex.common.velocity.plugin.ConfigurablePlugin;
import com.dev7ex.common.velocity.plugin.VelocityPlugin;
import com.dev7ex.common.velocity.plugin.statistic.PluginStatisticProperties;
import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * Represents the VelocityCommon plugin for Velocity servers.
 * This plugin provides common functionality and serves as a library for Minecraft servers running on Velocity.
 * It initializes the plugin and handles configuration loading.
 *
 * @author Dev7ex
 * @since 15.05.2024
 */
@Getter(AccessLevel.PUBLIC)
@Plugin(id = "faciliscommon", name = "FacilisCommon", version = "1.0.6-SNAPSHOT",
        description = "Library for Minecraft", authors = {"Dev7ex"})
@PluginStatisticProperties(identification = 23029, enabled = true)
public class VelocityCommonPlugin extends VelocityPlugin implements ConfigurablePlugin {

    private VelocityCommonConfiguration configuration;

    /**
     * Constructs a new VelocityCommonPlugin object with the specified parameters.
     *
     * @param server        The ProxyServer object representing the Velocity server.
     * @param logger        The Logger object for logging messages.
     * @param dataDirectory The path to the data directory.
     */
    @Inject
    public VelocityCommonPlugin(@NotNull final ProxyServer server, @NotNull final Logger logger, @DataDirectory final Path dataDirectory) {
        super(server, logger, dataDirectory);

        VelocityCommon.setProxyServer(server);
    }

    @Override
    public void onLoad() {
        this.configuration = new VelocityCommonConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

}
