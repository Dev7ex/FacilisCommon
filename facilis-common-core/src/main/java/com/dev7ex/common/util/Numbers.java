package com.dev7ex.common.util;

import org.jetbrains.annotations.NotNull;

/**
 * Utility class for checking numeric types from strings.
 * Provides methods to check if a string can be parsed into byte, short, integer, long, float, or double.
 *
 * @author Dev7ex
 * @since 15.06.2023
 */
public class Numbers {

    /**
     * Private constructor to prevent initialization of the utility class.
     */
    private Numbers() {
    }

    /**
     * Checks if the specified value is a byte.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into a byte, false otherwise.
     */
    public static boolean isByte(@NotNull final String s) {
        try {
            Byte.parseByte(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the specified value is a short.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into a short, false otherwise.
     */
    public static boolean isShort(@NotNull final String s) {
        try {
            Short.parseShort(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the specified value is an integer.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into an integer, false otherwise.
     */
    public static boolean isInteger(@NotNull final String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the specified value is a long.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into a long, false otherwise.
     */
    public static boolean isLong(@NotNull final String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the specified value is a float.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into a float, false otherwise.
     */
    public static boolean isFloat(@NotNull final String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the specified value is a double.
     *
     * @param s The String to be checked.
     * @return true if the String can be parsed into a double, false otherwise.
     */
    public static boolean isDouble(@NotNull final String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

}
