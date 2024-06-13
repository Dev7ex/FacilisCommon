package com.dev7ex.common.bukkit.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define properties for Bukkit commands.
 * This annotation can be used to specify the name, aliases, and required permission for a command.
 * It should be applied to classes that extend {@link BukkitCommand}.
 *
 * @author Dev7ex
 * @since 19.07.2022
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BukkitCommandProperties {

    /**
     * The name of the command.
     *
     * @return the name of the command.
     */
    String name();

    /**
     * Aliases for the command.
     * These are alternative names that can be used to execute the command.
     *
     * @return an array of aliases for the command.
     */
    String[] aliases() default {};

    /**
     * The permission required to execute the command.
     * If the permission is not specified, the command can be executed by anyone.
     *
     * @return the permission required to execute the command.
     */
    String permission() default "";
}