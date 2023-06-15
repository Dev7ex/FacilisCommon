package com.dev7ex.common.util;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 15.06.2023
 */
public class Numbers {

    /**
     * Static class cannot be initialized.
     */
    private Numbers() {}

    /**
     * Checks if the specified value is a byte
     * @param s The String to be checked
     * @return true if the String is a byte
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
     * Checks if the specified value is a short
     * @param s The String to be checked
     * @return true if the String is a short
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
     * Checks if the specified value is an integer
     * @param s The String to be checked
     * @return true if the String is a byte
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
     * Checks if the specified value is a long
     * @param s The String to be checked
     * @return true if the String is a long
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
     * Checks if the specified value is a float
     * @param s The String to be checked
     * @return true if the String is a float
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
     * Checks if the specified value is a double
     * @param s The String to be checked
     * @return true if the String is a double
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
