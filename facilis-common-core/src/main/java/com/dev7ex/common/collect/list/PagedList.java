package com.dev7ex.common.collect.list;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * A utility class that encapsulates a list of elements and partitions it into pages, each containing a specified
 * number of elements. The class provides methods to access specific pages of the partitioned list.
 *
 * @param <E> the type of elements in this list
 * @author Dev7ex
 * @since 15.08.2024
 */
@Getter(AccessLevel.PUBLIC)
public class PagedList<E> {

    /**
     * The list of pages, where each page is a list containing a subset of elements from the original list.
     */
    private final List<List<E>> pages;

    /**
     * The total number of pages.
     */
    private final int pageAmount;

    /**
     * Constructs a PagedList by partitioning the provided list into pages, each containing the specified number of elements.
     *
     * @param elements      the list of elements to partition, must not be null
     * @param amountPerPage the maximum number of elements per page
     * @throws IllegalArgumentException if amountPerPage is less than or equal to 0
     */
    public PagedList(@NotNull final List<E> elements, final int amountPerPage) {
        if (amountPerPage <= 0) {
            throw new IllegalArgumentException("The amount per page must be greater than 0.");
        }
        this.pages = Lists.partition(elements, amountPerPage);
        this.pageAmount = this.pages.size();
    }

    /**
     * Returns an {@link Optional} containing the list of elements on the specified page.
     *
     * @param page the page number to retrieve (1-based index)
     * @return an {@link Optional} containing the list of elements on the specified page,
     * or an empty {@link Optional} if the page number is out of range
     */
    public Optional<List<E>> page(final int page) {
        if ((page <= 0) || (page > this.pageAmount)) {
            return Optional.empty();
        }
        return Optional.of(this.pages.get(page - 1));
    }

}
