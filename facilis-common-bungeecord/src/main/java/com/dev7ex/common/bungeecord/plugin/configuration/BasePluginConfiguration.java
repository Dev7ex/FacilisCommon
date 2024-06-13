package com.dev7ex.common.bungeecord.plugin.configuration;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface that defines methods to handle plugin configuration.
 * Implementations should provide methods to load and retrieve configuration values
 * of different types from a source (e.g., file, database).
 * <p>
 * This interface includes methods to retrieve configuration values such as strings,
 * numbers, booleans, characters, and lists of these types.
 *
 * @author Dev7ex
 * @since 16.12.2022
 */
public interface BasePluginConfiguration {

    /**
     * Loads the configuration.
     * Implementations should handle the process of loading configuration settings
     * from a specified source (e.g., file, database).
     */
    void load();

    /**
     * Retrieves the prefix string from the configuration.
     *
     * @return The prefix string configured for the plugin.
     */
    String getPrefix();

    /**
     * Retrieves the message displayed when a player has no permission.
     *
     * @return The no permission message configured for the plugin.
     */
    String getNoPermissionMessage();

    /**
     * Retrieves a string value from the configuration at the specified path.
     *
     * @param path The path to the string value in the configuration.
     * @return The string value retrieved from the configuration.
     */
    String getString(@NotNull final String path);

    /**
     * Retrieves a long value from the configuration at the specified path.
     *
     * @param path The path to the long value in the configuration.
     * @return The long value retrieved from the configuration.
     */
    long getLong(@NotNull final String path);

    /**
     * Retrieves an integer value from the configuration at the specified path.
     *
     * @param path The path to the integer value in the configuration.
     * @return The integer value retrieved from the configuration.
     */
    int getInteger(@NotNull final String path);

    /**
     * Retrieves a short value from the configuration at the specified path.
     *
     * @param path The path to the short value in the configuration.
     * @return The short value retrieved from the configuration.
     */
    short getShort(@NotNull final String path);

    /**
     * Retrieves a byte value from the configuration at the specified path.
     *
     * @param path The path to the byte value in the configuration.
     * @return The byte value retrieved from the configuration.
     */
    byte getByte(@NotNull final String path);

    /**
     * Retrieves a double value from the configuration at the specified path.
     *
     * @param path The path to the double value in the configuration.
     * @return The double value retrieved from the configuration.
     */
    double getDouble(@NotNull final String path);

    /**
     * Retrieves a float value from the configuration at the specified path.
     *
     * @param path The path to the float value in the configuration.
     * @return The float value retrieved from the configuration.
     */
    float getFloat(@NotNull final String path);

    /**
     * Retrieves a boolean value from the configuration at the specified path.
     *
     * @param path The path to the boolean value in the configuration.
     * @return The boolean value retrieved from the configuration.
     */
    boolean getBoolean(@NotNull final String path);

    /**
     * Retrieves a character value from the configuration at the specified path.
     *
     * @param path The path to the character value in the configuration.
     * @return The character value retrieved from the configuration.
     */
    char getCharacter(@NotNull final String path);

    /**
     * Retrieves a list of strings from the configuration at the specified path.
     *
     * @param path The path to the list of strings in the configuration.
     * @return The list of strings retrieved from the configuration.
     */
    List<String> getStringList(@NotNull final String path);

    /**
     * Retrieves a list of long values from the configuration at the specified path.
     *
     * @param path The path to the list of long values in the configuration.
     * @return The list of long values retrieved from the configuration.
     */
    List<Long> getLongList(@NotNull final String path);

    /**
     * Retrieves a list of integer values from the configuration at the specified path.
     *
     * @param path The path to the list of integer values in the configuration.
     * @return The list of integer values retrieved from the configuration.
     */
    List<Integer> getIntegerList(@NotNull final String path);

    /**
     * Retrieves a list of short values from the configuration at the specified path.
     *
     * @param path The path to the list of short values in the configuration.
     * @return The list of short values retrieved from the configuration.
     */
    List<Short> getShortList(@NotNull final String path);

    /**
     * Retrieves a list of byte values from the configuration at the specified path.
     *
     * @param path The path to the list of byte values in the configuration.
     * @return The list of byte values retrieved from the configuration.
     */
    List<Byte> getByteList(@NotNull final String path);

    /**
     * Retrieves a list of double values from the configuration at the specified path.
     *
     * @param path The path to the list of double values in the configuration.
     * @return The list of double values retrieved from the configuration.
     */
    List<Double> getDoubleList(@NotNull final String path);

    /**
     * Retrieves a list of float values from the configuration at the specified path.
     *
     * @param path The path to the list of float values in the configuration.
     * @return The list of float values retrieved from the configuration.
     */
    List<Float> getFloatList(@NotNull final String path);

    /**
     * Retrieves a list of boolean values from the configuration at the specified path.
     *
     * @param path The path to the list of boolean values in the configuration.
     * @return The list of boolean values retrieved from the configuration.
     */
    List<Boolean> getBooleanList(@NotNull final String path);

    /**
     * Retrieves a list of character values from the configuration at the specified path.
     *
     * @param path The path to the list of character values in the configuration.
     * @return The list of character values retrieved from the configuration.
     */
    List<Character> getCharacterList(@NotNull final String path);


}
