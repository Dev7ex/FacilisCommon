package com.dev7ex.common.bungeecord.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for identifying a BungeeCord plugin with additional metadata.
 * Use this annotation to specify the Spigot Resource ID associated with the plugin.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginIdentification {

    /**
     * Specifies the Spigot Resource ID associated with the plugin.
     *
     * @return the Spigot Resource ID (default: 0 if unspecified)
     */
    int spigotResourceId() default 0;

}
