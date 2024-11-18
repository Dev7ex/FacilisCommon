package com.dev7ex.common.database;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.10.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class DatabaseConnection {

    protected final DatabaseProperties properties;

    public DatabaseConnection(@NotNull final DatabaseProperties properties) {
        this.properties = properties;
    }

    public abstract void onConnect();

    public abstract void onDisconnect();

    public abstract boolean isConnected();

}
