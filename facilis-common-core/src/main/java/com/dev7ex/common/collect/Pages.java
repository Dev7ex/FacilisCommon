package com.dev7ex.common.collect;

import com.dev7ex.common.collect.list.PagedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating instances of {@link PagedList} from various types of input.
 * This class provides convenient static methods to partition lists or arrays of elements
 * into smaller, manageable pages based on the specified page size.
 *
 * @author Dev7ex
 * @since 15.08.2024
 */
public class Pages {

    /**
     * Creates a {@link PagedList} by partitioning the provided array of entries into pages of the specified size.
     *
     * @param <T>           the type of elements in the entries array
     * @param amountPerPage the number of elements per page
     * @param entries       the array of elements to be partitioned into pages
     * @return a {@link PagedList} containing the partitioned pages of elements
     * @throws IllegalArgumentException if {@code amountPerPage} is less than or equal to zero
     */
    @SafeVarargs
    public static <T> PagedList<T> create(final int amountPerPage, @Nullable final T... entries) {
        if (amountPerPage <= 0) {
            throw new IllegalArgumentException("amountPerPage must be greater than zero.");
        }
        return new PagedList<>(Arrays.asList(entries), amountPerPage);
    }

    /**
     * Creates a {@link PagedList} by partitioning the provided list of entries into pages of the specified size.
     *
     * @param <T>           the type of elements in the entries list
     * @param amountPerPage the number of elements per page
     * @param entries       the list of elements to be partitioned into pages
     * @return a {@link PagedList} containing the partitioned pages of elements
     * @throws IllegalArgumentException if {@code amountPerPage} is less than or equal to zero
     * @throws NullPointerException     if {@code entries} is null
     */
    public static <T> PagedList<T> create(final int amountPerPage, @NotNull final List<T> entries) {
        if (amountPerPage <= 0) {
            throw new IllegalArgumentException("amountPerPage must be greater than zero.");
        }
        return new PagedList<>(entries, amountPerPage);
    }

}
