package com.dev7ex.common.bukkit.scoreboard.game;

/**
 * @author Dev7ex
 * @since 20.08.2024
 */

import org.bukkit.Bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Small reflection utility class to use CraftBukkit and NMS.
 *
 * @author MrMicky
 */
public final class GameScoreboardReflection {

    private static final String NM_PACKAGE = "net.minecraft";
    private static final String OBC_PACKAGE = Bukkit.getServer().getClass().getPackage().getName();
    private static final String NMS_PACKAGE = OBC_PACKAGE.replace("org.bukkit.craftbukkit", NM_PACKAGE + ".server");

    private static final MethodType VOID_METHOD_TYPE = MethodType.methodType(void.class);
    private static final boolean NMS_REPACKAGED = optionalClass(NM_PACKAGE + ".network.protocol.Packet").isPresent();
    private static final boolean MOJANG_MAPPINGS = optionalClass(NM_PACKAGE + ".network.chat.Component").isPresent();

    private static volatile Object theUnsafe;

    private GameScoreboardReflection() {
        throw new UnsupportedOperationException();
    }

    public static boolean isRepackaged() {
        return NMS_REPACKAGED;
    }

    public static String nmsClassName(final String post1_17package, final String className) {
        if (NMS_REPACKAGED) {
            final String classPackage = post1_17package == null ? NM_PACKAGE : NM_PACKAGE + '.' + post1_17package;

            return classPackage + '.' + className;
        }

        return NMS_PACKAGE + '.' + className;
    }

    public static Class<?> nmsClass(final String post1_17package, final String className) throws ClassNotFoundException {
        return Class.forName(nmsClassName(post1_17package, className));
    }

    public static Class<?> nmsClass(final String post1_17package, final String spigotClass, final String mojangClass) throws ClassNotFoundException {
        return nmsClass(post1_17package, MOJANG_MAPPINGS ? mojangClass : spigotClass);
    }

    public static Optional<Class<?>> nmsOptionalClass(final String post1_17package, final String className) {
        return optionalClass(nmsClassName(post1_17package, className));
    }

    public static String obcClassName(final String className) {
        return OBC_PACKAGE + '.' + className;
    }

    public static Class<?> obcClass(final String className) throws ClassNotFoundException {
        return Class.forName(obcClassName(className));
    }

    public static Optional<Class<?>> obcOptionalClass(final String className) {
        return optionalClass(obcClassName(className));
    }

    public static Optional<Class<?>> optionalClass(final String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (final ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public static Object enumValueOf(final Class<?> enumClass, final String enumName) {
        return Enum.valueOf(enumClass.asSubclass(Enum.class), enumName);
    }

    public static Object enumValueOf(final Class<?> enumClass, final String enumName, final int fallbackOrdinal) {
        try {
            return enumValueOf(enumClass, enumName);
        } catch (final IllegalArgumentException e) {
            final Object[] constants = enumClass.getEnumConstants();
            if (constants.length > fallbackOrdinal) {
                return constants[fallbackOrdinal];
            }
            throw e;
        }
    }

    static Class<?> innerClass(final Class<?> parentClass, final Predicate<Class<?>> classPredicate) throws ClassNotFoundException {
        for (final Class<?> innerClass : parentClass.getDeclaredClasses()) {
            if (classPredicate.test(innerClass)) {
                return innerClass;
            }
        }
        throw new ClassNotFoundException("No class in " + parentClass.getCanonicalName() + " matches the predicate.");
    }

    static Optional<MethodHandle> optionalConstructor(final Class<?> declaringClass, final MethodHandles.Lookup lookup, final MethodType type) throws IllegalAccessException {
        try {
            return Optional.of(lookup.findConstructor(declaringClass, type));
        } catch (final NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    public static PacketConstructor findPacketConstructor(final Class<?> packetClass, final MethodHandles.Lookup lookup) throws Exception {
        try {
            final MethodHandle constructor = lookup.findConstructor(packetClass, VOID_METHOD_TYPE);
            return constructor::invoke;
        } catch (final NoSuchMethodException | IllegalAccessException e) {
            // try below with Unsafe
        }

        if (theUnsafe == null) {
            synchronized (GameScoreboardReflection.class) {
                if (theUnsafe == null) {
                    final Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                    final Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
                    theUnsafeField.setAccessible(true);
                    theUnsafe = theUnsafeField.get(null);
                }
            }
        }

        final MethodType allocateMethodType = MethodType.methodType(Object.class, Class.class);
        final MethodHandle allocateMethod = lookup.findVirtual(theUnsafe.getClass(), "allocateInstance", allocateMethodType);
        return () -> allocateMethod.invoke(theUnsafe, packetClass);
    }

    @FunctionalInterface
    interface PacketConstructor {
        Object invoke() throws Throwable;
    }

}
