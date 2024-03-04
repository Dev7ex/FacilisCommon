package com.dev7ex.common.collect.list;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Dev7ex
 * @since 03.03.2024
 * @param <E>
 */
public class ParsedList<E> extends ArrayList<E> {

    public ParsedList(final int initialCapacity) {
        super(initialCapacity);
    }

    public ParsedList(final Collection<? extends E> collection) {
        super(collection);
    }

    public String getString(final int index) {
        return super.get(index).toString();
    }

    public String getString(final int index, @NotNull final String defaultValue) {
        return ((super.get(index).toString() == null) ? (defaultValue) : (super.get(index).toString()));
    }

    public byte getByte(final int index) {
        return Byte.parseByte(this.getString(index));
    }

    public byte getByte(final int index, final byte defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Byte.parseByte(this.getString(index))));
    }

    public short getShort(final int index) {
        return Short.parseShort(this.getString(index));
    }

    public short getShort(final int index, final short defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Short.parseShort(this.getString(index))));
    }

    public int getInteger(final int index) {
        return Integer.parseInt(this.getString(index));
    }

    public int getInteger(final int index, final int defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Integer.parseInt(this.getString(index))));
    }

    public long getLong(final int index) {
        return Long.parseLong(this.getString(index));
    }

    public long getLong(final int index, final long defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Long.parseLong(this.getString(index))));
    }

    public float getFloat(final int index) {
        return Float.parseFloat(this.getString(index));
    }

    public float getFloat(final int index, final float defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Float.parseFloat(this.getString(index))));
    }

    public double getDouble(final int index) {
        return Double.parseDouble(this.getString(index));
    }

    public double getDouble(final int index, final double defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Double.parseDouble(this.getString(index))));
    }

    public boolean getBoolean(final int index) {
        return Boolean.parseBoolean(this.getString(index));
    }

    public boolean getBoolean(final int index, final boolean defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : (Boolean.parseBoolean(this.getString(index))));
    }

    public char getCharacter(final int index) {
        return this.getString(index).charAt(0);
    }

    public char getCharacter(final int index, final char defaultValue) {
        return ((this.getString(index) == null) ? (defaultValue) : this.getString(index).charAt(0));
    }

    public UUID getUUID(final int index) {
        return UUID.fromString(this.getString(index));
    }

}
