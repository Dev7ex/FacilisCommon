package com.dev7ex.common.database;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 05.03.2024
 */
public interface DatabaseObject<T> {

    DatabaseObject<T> read(@NotNull final T object);

    void write(@NotNull final T object);

}
