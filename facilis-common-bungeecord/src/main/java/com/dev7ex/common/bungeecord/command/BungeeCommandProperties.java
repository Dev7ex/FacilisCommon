package com.dev7ex.common.bungeecord.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for defining properties of a BungeeCord proxy command.
 * Use this annotation to specify the name, permission, and aliases for a command class.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BungeeCommandProperties {

    /**
     * The name of the command.
     *
     * @return the name of the command
     */
    String name();

    /**
     * The permission required to execute the command.
     *
     * @return the permission string (default: empty)
     */
    String permission() default "";

    /**
     * The aliases for the command.
     *
     * @return an array of alias strings (default: empty)
     */
    String[] aliases() default "";

}
