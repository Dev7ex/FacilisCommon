package com.dev7ex.common.collect;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;

/**
 * A simple key-value pair implementation.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
@Getter(AccessLevel.PUBLIC)
public class Pair<K, V> implements Map.Entry<K, V> {

    private final K key;
    private final V value;

    /**
     * Constructs a new Pair with the specified key and value.
     *
     * @param key   The key of the pair.
     * @param value The value of the pair.
     */
    public Pair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Unsupported operation. This method is not supported and will throw UnsupportedOperationException if called.
     *
     * @param value The new value to set.
     * @return The old value, which is not set.
     * @throws UnsupportedOperationException Always thrown as this operation is not supported.
     */
    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException("Pair does not support setValue operation.");
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        final Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(this.key, pair.key) &&
                Objects.equals(this.value, pair.value);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.key, this.value);
    }

    /**
     * Returns the string representation of the pair.
     *
     * @return The string representation of the pair.
     */
    @Override
    public String toString() {
        return "(" + this.key + ", " + this.value + ")";
    }
}
