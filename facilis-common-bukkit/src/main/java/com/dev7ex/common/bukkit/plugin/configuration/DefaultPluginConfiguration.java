package com.dev7ex.common.bukkit.plugin.configuration;

import com.dev7ex.common.bukkit.configuration.ConfigurationBase;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * @author Dev7ex
 * @since 16.12.2022
 */
public abstract class DefaultPluginConfiguration extends ConfigurationBase implements BasePluginConfiguration {

    public DefaultPluginConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        super.copyFile();
        super.loadFile();
    }

    @Override
    public DefaultArtifactVersion getVersion() {
        return new DefaultArtifactVersion(Objects.requireNonNull(this.getVersionAsString()));
    }

    @Override
    public String getVersionAsString() {
        return super.getFileConfiguration().getString("config-version");
    }

    public boolean isNewestVersion(final ArtifactVersion currentVersion) {
        return (this.getVersion().compareTo(currentVersion) >= 0);
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
        return this.getFileConfiguration().getCharacterList(path);
    }

}
