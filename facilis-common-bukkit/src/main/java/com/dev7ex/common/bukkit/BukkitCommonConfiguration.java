package com.dev7ex.common.bukkit;

import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;
import com.dev7ex.common.io.file.configuration.ConfigurationHolder;
import com.dev7ex.common.io.file.configuration.ConfigurationProperties;
import com.dev7ex.common.io.file.configuration.FileConfiguration;
import com.dev7ex.common.io.file.configuration.YamlConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Configuration class for the BukkitCommon plugin.
 * Handles loading, saving, and managing plugin configuration.
 * Provides default values and removes obsolete entries from the configuration file.
 * Uses YAML file format for configuration storage.
 *
 * @author Dev7ex
 * @since 04.01.2022
 */
@ConfigurationProperties(fileName = "config.yml", provider = YamlConfiguration.class)
public class BukkitCommonConfiguration extends DefaultPluginConfiguration {

    /**
     * Constructs a new BukkitCommonConfiguration instance.
     *
     * @param configurationHolder ConfigurationHolder instance to manage configuration.
     */
    public BukkitCommonConfiguration(@NotNull final ConfigurationHolder configurationHolder) {
        super(configurationHolder);
    }

    /**
     * Loads the plugin configuration.
     * Checks for missing or obsolete entries and updates the configuration file accordingly.
     */
    @Override
    public void load() {
        super.load();

        final FileConfiguration config = super.getFileConfiguration();

        for (final Entry entry : Entry.values()) {
            if ((entry.isRemoved()) && (config.contains(entry.getPath()))) {
                config.set(entry.getPath(), null);
                BukkitCommonPlugin.getInstance().getLogger().info("Removed unnecessary config entry: " + entry.getPath());
            }

            if ((!entry.isRemoved()) && (!config.contains(entry.getPath()))) {
                BukkitCommonPlugin.getInstance().getLogger().info("Adding missing config entry: " + entry.getPath());
                config.set(entry.getPath(), entry.getDefaultValue());
            }
        }
        super.saveFile();
    }

    /**
     * Enum representing configuration entries with default values.
     */
    @Getter(AccessLevel.PUBLIC)
    public enum Entry {
        PREFIX("prefix", "§8[§cFacilisCommon§8]§r", false),
        NO_PERMISSION("no-permission", "§cIm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that is in error.", false),
        SETTINGS_RECEIVE_UPDATE_MESSAGE("settings.receive-update-message", true, true),
        SETTINGS_UPDATE_MESSAGE_PLAYER("settings.update-message-player", "", true),
        SETTINGS_UPDATE_MESSAGE_VERSION_PLAYER("settings.update-message-version-player", "", true);

        private final String path;
        private final Object defaultValue;
        private final boolean removed;

        /**
         * Constructs a new Entry enum instance.
         *
         * @param path         Configuration path.
         * @param defaultValue Default value for the configuration entry.
         * @param removed      Indicates whether the entry should be removed if not present.
         */
        Entry(@NotNull final String path, @NotNull final Object defaultValue, final boolean removed) {
            this.path = path;
            this.defaultValue = defaultValue;
            this.removed = removed;
        }
    }
}
