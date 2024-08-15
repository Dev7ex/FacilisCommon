package com.dev7ex.common.collect.list;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * A custom list implementation that extends {@link ArrayList} and provides utility methods to retrieve
 * elements in various data types, such as {@code String}, {@code int}, {@code boolean}, and more.
 * This class is designed to simplify the process of parsing and retrieving elements from a list,
 * particularly when the elements are expected to be of different types.
 *
 * @param <E> the type of elements in this list
 * @author Dev7ex
 * @since 03.03.2024
 */
public class ParsedList<E> extends ArrayList<E> {

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     */
    public ParsedList(final int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are returned by
     * the collection's iterator.
     *
     * @param collection the collection whose elements are to be placed into this list
     */
    public ParsedList(final Collection<? extends E> collection) {
        super(collection);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a string.
     *
     * @param index the index of the element to return
     * @return the string representation of the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getString(final int index) {
        return super.get(index).toString();
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a string.
     * Returns the specified default value if the element is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the element is null
     * @return the string representation of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getString(final int index, @NotNull final String defaultValue) {
        final String value = super.get(index).toString();
        return (value == null) ? defaultValue : value;
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a byte.
     *
     * @param index the index of the element to return
     * @return the byte value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a byte
     */
    public byte getByte(final int index) {
        return Byte.parseByte(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a byte.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the byte value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a byte
     */
    public byte getByte(final int index, final byte defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Byte.parseByte(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a short.
     *
     * @param index the index of the element to return
     * @return the short value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a short
     */
    public short getShort(final int index) {
        return Short.parseShort(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a short.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the short value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a short
     */
    public short getShort(final int index, final short defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Short.parseShort(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to an integer.
     *
     * @param index the index of the element to return
     * @return the integer value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as an integer
     */
    public int getInteger(final int index) {
        return Integer.parseInt(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to an integer.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the integer value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as an integer
     */
    public int getInteger(final int index, final int defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Integer.parseInt(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a long.
     *
     * @param index the index of the element to return
     * @return the long value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a long
     */
    public long getLong(final int index) {
        return Long.parseLong(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a long.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the long value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a long
     */
    public long getLong(final int index, final long defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Long.parseLong(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a float.
     *
     * @param index the index of the element to return
     * @return the float value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a float
     */
    public float getFloat(final int index) {
        return Float.parseFloat(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a float.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the float value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a float
     */
    public float getFloat(final int index, final float defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Float.parseFloat(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a double.
     *
     * @param index the index of the element to return
     * @return the double value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a double
     */
    public double getDouble(final int index) {
        return Double.parseDouble(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a double.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the double value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NumberFormatException     if the string cannot be parsed as a double
     */
    public double getDouble(final int index, final double defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Double.parseDouble(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a boolean.
     *
     * @param index the index of the element to return
     * @return the boolean value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean getBoolean(final int index) {
        return Boolean.parseBoolean(this.getString(index));
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a boolean.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the boolean value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean getBoolean(final int index, final boolean defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : Boolean.parseBoolean(value);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a character.
     *
     * @param index the index of the element to return
     * @return the character value of the element at the specified position
     * @throws IndexOutOfBoundsException       if the index is out of range
     * @throws StringIndexOutOfBoundsException if the string is empty
     */
    public char getCharacter(final int index) {
        return this.getString(index).charAt(0);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a character.
     * Returns the specified default value if the string is null.
     *
     * @param index        the index of the element to return
     * @param defaultValue the default value to return if the string is null
     * @return the character value of the element at the specified position, or the default value if null
     * @throws IndexOutOfBoundsException       if the index is out of range
     * @throws StringIndexOutOfBoundsException if the string is empty
     */
    public char getCharacter(final int index, final char defaultValue) {
        final String value = this.getString(index);
        return (value == null) ? defaultValue : value.charAt(0);
    }

    /**
     * Retrieves the element at the specified position in this list and converts it to a {@link UUID}.
     *
     * @param index the index of the element to return
     * @return the UUID value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws IllegalArgumentException  if the string cannot be parsed as a UUID
     */
    public UUID getUUID(final int index) {
        return UUID.fromString(this.getString(index));
    }

}
