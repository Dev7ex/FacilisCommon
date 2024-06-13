package com.dev7ex.common.bungeecord.plugin;

/**
 * Interface representing a BungeeCord plugin that interacts with a database.
 * Implement this interface in plugins that need to manage database connections.
 * <p>
 * Implement {@link #onConnect()} to handle database connection setup.
 * Implement {@link #onDisconnect()} to handle database disconnection cleanup.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
public interface DatabasePlugin {

    /**
     * Called when the plugin needs to establish a database connection.
     * Implement this method to perform any necessary initialization tasks.
     */
    void onConnect();

    /**
     * Called when the plugin needs to disconnect from the database.
     * Implement this method to perform any necessary cleanup tasks.
     */
    void onDisconnect();

}
