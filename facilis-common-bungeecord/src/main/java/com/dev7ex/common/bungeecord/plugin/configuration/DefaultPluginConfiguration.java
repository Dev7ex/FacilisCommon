package com.dev7ex.common.bungeecord.plugin.configuration;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.io.file.configuration.Configuration;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Abstract base class for plugin configurations in a BungeeCord environment.
 * <p>
 * This class extends Configuration and implements BasePluginConfiguration,
 * providing methods to load and access various types of configuration values
 * from a file.
 * <p>
 * The configuration is associated with a ConfigurablePlugin instance, allowing
 * plugins to manage their configurations through a unified interface.
 *
 * @author Dev7ex
 * @since 16.12.2022
 */
@Getter(AccessLevel.PUBLIC)
public abstract class DefaultPluginConfiguration extends Configuration implements BasePluginConfiguration {

    /**
     * Constructs a DefaultPluginConfiguration instance associated with the specified plugin.
     *
     * @param plugin The plugin instance that owns this configuration.
     */
    public DefaultPluginConfiguration(@NotNull final ConfigurablePlugin plugin) {
        super(plugin);
    }

    /**
     * Loads the configuration file, copying it if necessary.
     * This method overrides the corresponding method in Configuration.
     */
    @Override
    public void load() {
        super.copyFile();
        super.loadFile();
    }

    /**
     * Retrieves the prefix string defined in the configuration.
     *
     * @return The prefix string.
     */
    @Override
    public String getPrefix() {
        return this.getString("prefix");
    }

    /**
     * Retrieves the no-permission message defined in the configuration.
     *
     * @return The no-permission message string.
     */
    @Override
    public String getNoPermissionMessage() {
        return this.getString("no-permission");
    }

    /**
     * Retrieves a string value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The string value.
     */
    @Override
    public String getString(@NotNull final String path) {
        return this.getFileConfiguration().getString(path);
    }

    /**
     * Retrieves a long value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The long value.
     */
    @Override
    public long getLong(@NotNull final String path) {
        return this.getFileConfiguration().getLong(path);
    }

    /**
     * Retrieves an integer value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The integer value.
     */
    @Override
    public int getInteger(@NotNull final String path) {
        return this.getFileConfiguration().getInt(path);
    }

    /**
     * Retrieves a short value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The short value.
     */
    @Override
    public short getShort(@NotNull final String path) {
        return (short) this.getFileConfiguration().getDouble(path);
    }

    /**
     * Retrieves a byte value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The byte value.
     */
    @Override
    public byte getByte(@NotNull final String path) {
        return (byte) this.getFileConfiguration().getInt(path);
    }

    /**
     * Retrieves a double value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The double value.
     */
    @Override
    public double getDouble(@NotNull final String path) {
        return this.getFileConfiguration().getDouble(path);
    }

    /**
     * Retrieves a float value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The float value.
     */
    @Override
    public float getFloat(@NotNull final String path) {
        return (float) this.getFileConfiguration().getDouble(path);
    }

    /**
     * Retrieves a boolean value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The boolean value.
     */
    @Override
    public boolean getBoolean(@NotNull final String path) {
        return this.getFileConfiguration().getBoolean(path);
    }

    /**
     * Retrieves a character value from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The character value.
     */
    @Override
    public char getCharacter(@NotNull final String path) {
        return Objects.requireNonNull(this.getFileConfiguration().getString(path)).charAt(0);
    }

    /**
     * Retrieves a list of string values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of string values.
     */
    @Override
    public List<String> getStringList(@NotNull final String path) {
        return this.getFileConfiguration().getStringList(path);
    }

    /**
     * Retrieves a list of long values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of long values.
     */
    @Override
    public List<Long> getLongList(@NotNull final String path) {
        return this.getFileConfiguration().getLongList(path);
    }

    /**
     * Retrieves a list of integer values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of integer values.
     */
    @Override
    public List<Integer> getIntegerList(@NotNull final String path) {
        return this.getFileConfiguration().getIntegerList(path);
    }

    /**
     * Retrieves a list of short values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of short values.
     */
    @Override
    public List<Short> getShortList(@NotNull final String path) {
        return this.getFileConfiguration().getShortList(path);
    }

    /**
     * Retrieves a list of byte values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of byte values.
     */
    @Override
    public List<Byte> getByteList(@NotNull final String path) {
        return this.getFileConfiguration().getByteList(path);
    }

    /**
     * Retrieves a list of double values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of double values.
     */
    @Override
    public List<Double> getDoubleList(@NotNull final String path) {
        return this.getFileConfiguration().getDoubleList(path);
    }

    /**
     * Retrieves a list of float values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of float values.
     */
    @Override
    public List<Float> getFloatList(@NotNull final String path) {
        return this.getFileConfiguration().getFloatList(path);
    }

    /**
     * Retrieves a list of boolean values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of boolean values.
     */
    @Override
    public List<Boolean> getBooleanList(@NotNull final String path) {
        return this.getFileConfiguration().getBooleanList(path);
    }

    /**
     * Retrieves a list of character values from the configuration at the specified path.
     *
     * @param path The path to the configuration value.
     * @return The list of character values.
     */
    @Override
    public List<Character> getCharacterList(@NotNull final String path) {
        return this.getFileConfiguration().getCharList(path);
    }

}
