package com.dev7ex.common.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Utility class for working with boolean values represented as strings.
 * Supports checking if a string value represents a boolean.
 *
 * @author Dev7ex
 * @since 29.03.2024
 */
public class Booleans {

    /**
     * Checks if the given string represents a boolean value.
     * The method checks against the following boolean representations:
     * "true", "false", "1", "0" (case insensitive).
     *
     * @param value The string value to check.
     * @return true if the value represents a boolean, false otherwise.
     */
    public static boolean isBoolean(@NotNull final String value) {
        return Arrays.stream(new String[]{"true", "false", "1", "0"})
                .anyMatch(b -> b.equalsIgnoreCase(value));
    }

}
