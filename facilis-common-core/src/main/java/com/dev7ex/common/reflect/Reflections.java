package com.dev7ex.common.reflect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Dev7ex
 * @since 18.05.2024
 */
public class Reflections {

    /**
     * Get a class by its name.
     *
     * @param name The fully qualified name of the class.
     * @return The class object or null if the class was not found.
     */
    public static Class<?> getClass(@NotNull final String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a constructor of a class with specific parameter types.
     *
     * @param clazz The class object.
     * @param parameterTypes The parameter types of the constructor.
     * @return The constructor object or null if the constructor was not found.
     */
    public static Constructor<?> getConstructor(@NotNull final Class<?> clazz, @Nullable final Class<?>... parameterTypes) {
        try {
            return clazz.getConstructor(parameterTypes);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Instantiate a new object using a constructor.
     *
     * @param constructor The constructor object.
     * @param arguments The arguments for the constructor.
     * @return The new instance or null if an error occurred.
     */
    public static Object newInstance(@NotNull final Constructor<?> constructor, @Nullable final Object... arguments) {
        try {
            return constructor.newInstance(arguments);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a field of a class by its name.
     *
     * @param clazz The class object.
     * @param name The name of the field.
     * @return The field object or null if the field was not found.
     */
    public static Field getField(@NotNull final Class<?> clazz, @NotNull final String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a method of a class by its name and parameter types.
     *
     * @param clazz The class object.
     * @param name The name of the method.
     * @param parameterTypes The parameter types of the method.
     * @return The method object or null if the method was not found.
     */
    public static Method getMethod(@NotNull final Class<?> clazz, @NotNull final String name, @Nullable final Class<?>... parameterTypes) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Set the value of a field in an object.
     *
     * @param field The field object.
     * @param target The object in which the field value will be set.
     * @param value The new value for the field.
     */
    public static void setFieldValue(@NotNull final Field field, @NotNull final Object target, @Nullable final Object value) {
        try {
            field.set(target, value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the value of a field in an object.
     *
     * @param field The field object.
     * @param target The object from which the field value will be retrieved.
     * @return The value of the field or null if an error occurred.
     */
    public static Object getFieldValue(@NotNull final Field field, @NotNull final Object target) {
        try {
            return field.get(target);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Invoke a method on an object.
     *
     * @param method The method object.
     * @param target The object on which the method will be invoked.
     * @param arguments The arguments for the method.
     * @return The result of the method invocation or null if an error occurred.
     */
    public static Object invokeMethod(@NotNull final Method method, @NotNull final Object target, @Nullable final Object... arguments) {
        try {
            return method.invoke(target, arguments);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
