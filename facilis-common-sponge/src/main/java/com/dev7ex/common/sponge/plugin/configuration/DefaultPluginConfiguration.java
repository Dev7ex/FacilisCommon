package com.dev7ex.common.sponge.plugin.configuration;

import com.dev7ex.common.io.file.configuration.Configuration;
import com.dev7ex.common.io.file.configuration.ConfigurationHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * @author Dev7ex
 * @since 29.08.2024
 */
public abstract class DefaultPluginConfiguration extends Configuration implements BasePluginConfiguration {

    /**
     * Constructs a new instance of {@code DefaultPluginConfiguration}.
     *
     * @param configurationHolder The configuration holder from which to load configuration.
     */
    public DefaultPluginConfiguration(@NotNull final ConfigurationHolder configurationHolder) {
        super(configurationHolder);
    }

    /**
     * Loads the configuration file by copying it if necessary and then loading its contents.
     */
    @Override
    public void load() {
        super.copyFile();
        super.loadFile();
    }

    @Override
    public String getPrefix() {
        return this.getString("prefix");
    }

    @Override
    public String getNoPermissionMessage() {
        return this.getString("no-permission");
    }

    @Override
    public String getString(@NotNull final String path) {
        return this.getFileConfiguration().getString(path);
    }

    @Override
    public long getLong(@NotNull final String path) {
        return this.getFileConfiguration().getLong(path);
    }

    @Override
    public int getInteger(@NotNull final String path) {
        return this.getFileConfiguration().getInt(path);
    }

    @Override
    public short getShort(@NotNull final String path) {
        return (short) this.getFileConfiguration().getDouble(path);
    }

    @Override
    public byte getByte(@NotNull final String path) {
        return (byte) this.getFileConfiguration().getInt(path);
    }

    @Override
    public double getDouble(@NotNull final String path) {
        return this.getFileConfiguration().getDouble(path);
    }

    @Override
    public float getFloat(@NotNull final String path) {
        return (float) this.getFileConfiguration().getDouble(path);
    }

    @Override
    public boolean getBoolean(@NotNull final String path) {
        return this.getFileConfiguration().getBoolean(path);
    }

    @Override
    public char getCharacter(@NotNull final String path) {
        return Objects.requireNonNull(this.getFileConfiguration().getString(path)).charAt(0);
    }

    @Override
    public List<String> getStringList(@NotNull final String path) {
        return this.getFileConfiguration().getStringList(path);
    }

    @Override
    public List<Long> getLongList(@NotNull final String path) {
        return this.getFileConfiguration().getLongList(path);
    }

    @Override
    public List<Integer> getIntegerList(@NotNull final String path) {
        return this.getFileConfiguration().getIntegerList(path);
    }

    @Override
    public List<Short> getShortList(@NotNull final String path) {
        return this.getFileConfiguration().getShortList(path);
    }

    @Override
    public List<Byte> getByteList(@NotNull final String path) {
        return this.getFileConfiguration().getByteList(path);
    }

    @Override
    public List<Double> getDoubleList(@NotNull final String path) {
        return this.getFileConfiguration().getDoubleList(path);
    }

    @Override
    public List<Float> getFloatList(@NotNull final String path) {
        return this.getFileConfiguration().getFloatList(path);
    }

    @Override
    public List<Boolean> getBooleanList(@NotNull final String path) {
        return this.getFileConfiguration().getBooleanList(path);
    }

    @Override
    public List<Character> getCharacterList(@NotNull final String path) {
        return this.getFileConfiguration().getCharList(path);
    }

}
