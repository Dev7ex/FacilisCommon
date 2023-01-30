package com.dev7ex.common.bukkit.plugin.configuration;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.List;

/**
 * @author Dev7ex
 * @since 16.12.2022
 */
public interface BasePluginConfiguration {

    void load();

    String getPrefix();

    String getNoPermissionMessage();

    DefaultArtifactVersion getVersion();

    String getVersionAsString();

    String getString(final String path);

    long getLong(final String path);

    int getInteger(final String path);

    short getShort(final String path);

    byte getByte(final String path);

    double getDouble(final String path);

    float getFloat(final String path);

    boolean getBoolean(final String path);

    char getCharacter(final String path);

    List<String> getStringList(final String path);

    List<Long> getLongList(final String path);

    List<Integer> getIntegerList(final String path);

    List<Short> getShortList(final String path);

    List<Byte> getByteList(final String path);

    List<Double> getDoubleList(final String path);

    List<Float> getFloatList(final String path);

    List<Boolean> getBooleanList(final String path);

    List<Character> getCharacterList(final String path);

}
