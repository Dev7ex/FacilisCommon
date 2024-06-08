package com.dev7ex.common.io.array;

import org.jetbrains.annotations.NotNull;

/**
 * A generic array wrapper that provides basic iteration functionality.
 *
 * @param <T> the type of elements in this array
 * @since 26.03.2023
 */
public class Array<T> {

    // The underlying array of objects
    private final T[] objects;

    // The index of the next element to be returned
    private int nextIndex = 0;

    /**
     * Constructs an Array with the specified elements.
     *
     * @param objects the array of objects to be wrapped
     * @throws NullPointerException if the specified array is null
     */
    public Array(@NotNull final T[] objects) {
        this.objects = objects;
    }

    /**
     * Returns true if the iteration has more elements.
     *
     * @return true if there are more elements to return, false otherwise
     */
    public boolean hasNext() {
        return (this.nextIndex < this.objects.length);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws ArrayIndexOutOfBoundsException if there are no more elements
     */
    public T getNext() {
        return this.objects[this.nextIndex++];
    }

}
