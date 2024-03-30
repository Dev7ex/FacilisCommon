package com.dev7ex.common.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author Dev7ex
 * @since 29.03.2024
 */
public class Booleans {

    public static boolean isBoolean(@NotNull final String value) {
        return Arrays.stream(new String[]{"true", "false", "1", "0"})
                .anyMatch(b -> b.equalsIgnoreCase(value));
    }

}
