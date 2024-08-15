package com.dev7ex.common.reference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dev7ex
 * @since 02.02.2021
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public final class MultiReference<E, V> {

    private E firstValue;
    private V secondValue;

    public MultiReference(final E firstValue, final V secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

}
