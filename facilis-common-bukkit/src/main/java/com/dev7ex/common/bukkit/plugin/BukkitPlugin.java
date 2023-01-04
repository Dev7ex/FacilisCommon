package com.dev7ex.common.bukkit.plugin;

import com.dev7ex.common.bukkit.plugin.configuration.BasePluginConfiguration;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
public abstract class BukkitPlugin extends PluginBase {

    public void registerCommands() {}

    public void registerListeners() {}

    public void registerServices() {}

    public abstract <T extends BasePluginConfiguration> T getConfiguration();

}
