package com.dev7ex.common.io.file.configuration.serialization;

import com.dev7ex.common.io.file.configuration.FileConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Enum for handling serialization and deserialization of different data types in file configurations.
 * This enum provides methods to read and write values of various data types from and to file configurations.
 *
 * @since 07.06.2024
 */
@Getter(AccessLevel.PUBLIC)
public abstract class TypeSerialization<T> {

    public static TypeSerialization<Byte> BYTE = new TypeSerialization<>() {

        @Override
        public Byte write(@NotNull final FileConfiguration configuration, @NotNull final String path, @NotNull final Byte value) {
            configuration.set(path, value);
            return value;
        }

        @Override
        public Byte read(@NotNull final FileConfiguration configuration, @NotNull final String path) {
            return configuration.getByte(path);
        }
    };

    public static final TypeSerialization<Short> SHORT = new TypeSerialization<>() {

        @Override
        public Short write(@NotNull final FileConfiguration configuration, @NotNull final String path, @NotNull final Short value) {
            configuration.set(path, value);
            return value;
        }

        @Override
        public Short read(@NotNull final FileConfiguration configuration, @NotNull final String path) {
            return (short) configuration.getInt(path);
        }
    };

    public abstract T write(@NotNull final FileConfiguration configuration, @NotNull final String path, @NotNull final T value);

    public abstract T read(@NotNull final FileConfiguration configuration, @NotNull final String path);

}
