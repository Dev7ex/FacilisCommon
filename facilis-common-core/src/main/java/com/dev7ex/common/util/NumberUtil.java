package com.dev7ex.common.util;

/**
 * @author Dev7ex
 * @since 09.07.2022
 */
@Deprecated
public class NumberUtil {

    private NumberUtil() {}

    public static boolean isNumber(final String s) {
        try {
            Integer.parseInt(s);
            return true;

        } catch (final NumberFormatException exception) {
            return false;
        }
    }

}
