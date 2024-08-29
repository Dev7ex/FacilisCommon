package com.dev7ex.common.sponge.plugin.configuration;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for accessing configuration values in a plugin.
 * Implement this interface to provide methods for loading and retrieving
 * configuration settings such as strings, numbers, booleans, and lists.
 * <p>
 * Methods in this interface throw {@link NullPointerException} if the
 * provided {@code path} parameter is {@code null}, and may throw
 * {@link ClassCastException} if the retrieved value is not of the expected type.
 *
 * @author Dev7ex
 * @since 16.12.2022
 */
public interface BasePluginConfiguration {

    /**
     * Loads the configuration settings.
     * Implement this method to initialize the configuration values.
     */
    void load();

    /**
     * Retrieves the prefix for messages in the plugin.
     *
     * @return the prefix string
     */
    String getPrefix();

    /**
     * Retrieves the message to display when a player has no permission.
     *
     * @return the no permission message
     */
    String getNoPermissionMessage();

    String getString(@NotNull final String path);

    long getLong(@NotNull final String path);

    int getInteger(@NotNull final String path);

    short getShort(@NotNull final String path);

    byte getByte(@NotNull final String path);

    double getDouble(@NotNull final String path);

    float getFloat(@NotNull final String path);

    boolean getBoolean(@NotNull final String path);

    char getCharacter(@NotNull final String path);

    List<String> getStringList(@NotNull final String path);

    List<Long> getLongList(@NotNull final String path);

    List<Integer> getIntegerList(@NotNull final String path);

    List<Short> getShortList(@NotNull final String path);

    List<Byte> getByteList(@NotNull final String path);

    List<Double> getDoubleList(@NotNull final String path);

    List<Float> getFloatList(@NotNull final String path);

    List<Boolean> getBooleanList(@NotNull final String path);

    List<Character> getCharacterList(@NotNull final String path);

}
