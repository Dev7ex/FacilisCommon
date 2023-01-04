package com.dev7ex.common.bukkit.plugin.configuration;

import com.dev7ex.common.bukkit.configuration.ConfigurationBase;

import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

/**
 * @author Dev7ex
 * @since 16.12.2022
 */
public abstract class DefaultPluginConfiguration extends ConfigurationBase implements BasePluginConfiguration {

    public DefaultPluginConfiguration(final Plugin plugin) {
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
    public String getString(final String path) {
        return this.getFileConfiguration().getString(path);
    }

    @Override
    public long getLong(final String path) {
        return this.getFileConfiguration().getLong(path);
    }

    @Override
    public int getInteger(final String path) {
        return this.getFileConfiguration().getInt(path);
    }

    @Override
    public short getShort(final String path) {
        return (short) this.getFileConfiguration().getDouble(path);
    }

    @Override
    public byte getByte(final String path) {
        return (byte) this.getFileConfiguration().getInt(path);
    }

    @Override
    public double getDouble(final String path) {
        return this.getFileConfiguration().getDouble(path);
    }

    @Override
    public float getFloat(final String path) {
        return (float) this.getFileConfiguration().getDouble(path);
    }

    @Override
    public boolean getBoolean(final String path) {
        return this.getFileConfiguration().getBoolean(path);
    }

    @Override
    public char getCharacter(String path) {
        return Objects.requireNonNull(this.getFileConfiguration().getString(path)).charAt(0);
    }

    @Override
    public List<String> getStringList(final String path) {
        return this.getFileConfiguration().getStringList(path);
    }

    @Override
    public List<Long> getLongList(final String path) {
        return this.getFileConfiguration().getLongList(path);
    }

    @Override
    public List<Integer> getIntegerList(final String path) {
        return this.getFileConfiguration().getIntegerList(path);
    }

    public List<Short> getShortList(final String path) {
        return this.getFileConfiguration().getShortList(path);
    }

    public List<Byte> getByteList(final String path) {
        return this.getFileConfiguration().getByteList(path);
    }

    public List<Double> getDoubleList(final String path) {
        return this.getFileConfiguration().getDoubleList(path);
    }

    public List<Float> getFloatList(final String path) {
        return this.getFileConfiguration().getFloatList(path);
    }

    public List<Boolean> getBooleanList(final String path) {
        return this.getFileConfiguration().getBooleanList(path);
    }

    public List<Character> getCharacterList(final String path){
        return this.getFileConfiguration().getCharacterList(path);
    }

}
