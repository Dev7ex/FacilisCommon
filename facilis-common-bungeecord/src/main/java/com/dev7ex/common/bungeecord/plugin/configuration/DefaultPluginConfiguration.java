package com.dev7ex.common.bungeecord.plugin.configuration;

import com.dev7ex.common.bungeecord.plugin.ConfigurablePlugin;
import com.dev7ex.common.io.file.configuration.Configuration;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * @author Dev7ex
 * @since 16.12.2022
 */
@Getter(AccessLevel.PUBLIC)
public abstract class DefaultPluginConfiguration extends Configuration implements BasePluginConfiguration {

    public DefaultPluginConfiguration(@NotNull final ConfigurablePlugin plugin) {
        super(plugin);
    }

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
    public float getFloat(final @NotNull String path) {
        return (float) this.getFileConfiguration().getDouble(path);
    }

    @Override
    public boolean getBoolean(@NotNull final String path) {
        return this.getFileConfiguration().getBoolean(path);
    }

    @Override
    public char getCharacter(@NotNull String path) {
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

    public List<Short> getShortList(@NotNull final String path) {
        return this.getFileConfiguration().getShortList(path);
    }

    public List<Byte> getByteList(@NotNull final String path) {
        return this.getFileConfiguration().getByteList(path);
    }

    public List<Double> getDoubleList(@NotNull final String path) {
        return this.getFileConfiguration().getDoubleList(path);
    }

    public List<Float> getFloatList(@NotNull final String path) {
        return this.getFileConfiguration().getFloatList(path);
    }

    public List<Boolean> getBooleanList(@NotNull final String path) {
        return this.getFileConfiguration().getBooleanList(path);
    }

    public List<Character> getCharacterList(@NotNull final String path){
        return this.getFileConfiguration().getCharList(path);
    }

}
