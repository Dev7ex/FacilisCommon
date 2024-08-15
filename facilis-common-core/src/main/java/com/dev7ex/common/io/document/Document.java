package com.dev7ex.common.io.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 15.08.2024
 */
@Getter(AccessLevel.PUBLIC)
public class Document {

    private final String name;
    private final JsonObject dataCache;

    public Document(final String name, final JsonObject dataCache) {
        this.name = name;
        this.dataCache = dataCache;
    }

    public final Document append(final String key, final String value) {
        this.dataCache.addProperty(key, value);
        return this;
    }

    public final Document append(final String key, final Number value) {
        this.dataCache.addProperty(key, value);
        return this;
    }

    public final Document append(final String key, final Boolean value) {
        this.dataCache.addProperty(key, value);
        return this;
    }

    public final Document append(final String key, final JsonElement value) {
        this.dataCache.add(key, value);
        return this;
    }

    public String getString(@NotNull final String key) {
        return this.dataCache.get(key).getAsString();
    }

    public double getDouble(@NotNull final String key) {
        return this.dataCache.get(key).getAsDouble();
    }

    public int getInteger(@NotNull final String key) {
        return this.dataCache.get(key).getAsInt();
    }

    public long getLong(@NotNull final String key) {
        return this.dataCache.get(key).getAsLong();
    }

    public byte getByte(@NotNull final String key) {
        return this.dataCache.get(key).getAsByte();
    }

    public boolean getBoolean(@NotNull final String key) {
        return this.dataCache.get(key).getAsBoolean();
    }

    public short getShort(@NotNull final String key) {
        return this.dataCache.get(key).getAsShort();
    }

    public float getFloat(@NotNull final String key) {
        return this.dataCache.get(key).getAsFloat();
    }

}
