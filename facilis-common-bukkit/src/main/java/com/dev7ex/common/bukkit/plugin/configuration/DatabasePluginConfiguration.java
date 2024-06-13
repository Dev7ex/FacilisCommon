package com.dev7ex.common.bukkit.plugin.configuration;

import com.dev7ex.common.database.DatabaseProperties;

/**
 * Interface for database configuration specific to a Bukkit plugin.
 * Extends {@link DatabaseProperties} to inherit database-related methods
 * and properties.
 * <p>
 * This interface can be implemented to define additional methods or
 * configurations specific to the plugin's database setup.
 * <p>
 * Implementing classes should provide implementations for all methods
 * inherited from {@link DatabaseProperties}.
 *
 * @author Dev7ex
 * @since 17.02.2024
 */
public interface DatabasePluginConfiguration extends DatabaseProperties {
}
