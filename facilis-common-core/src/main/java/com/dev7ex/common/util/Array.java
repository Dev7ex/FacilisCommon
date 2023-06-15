package com.dev7ex.common.util;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 26.03.2023
 */
public class Array<T> {

    private final T[] objects;
    private int nextIndex = 0;

    public Array(@NotNull final T[] objects) {
        this.objects = objects;
    }

    public boolean hasNext() {
        return (this.nextIndex < this.objects.length);
    }

    public T getNext() {
        return this.objects[this.nextIndex++];
    }

}
