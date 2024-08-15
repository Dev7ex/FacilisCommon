package com.dev7ex.common.reference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Dev7ex
 * @since 02.02.2021
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public final class Reference<V> {

    private V value;

    public Reference(@NotNull final V value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if ((object == null) || (this.getClass() != object.getClass())) {
            return false;
        }
        final Reference<?> container = (Reference<?>) object;
        return Objects.equals(this.value, container.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

}
