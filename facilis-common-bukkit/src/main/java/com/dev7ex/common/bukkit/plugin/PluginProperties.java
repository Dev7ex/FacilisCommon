package com.dev7ex.common.bukkit.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dev7ex
 * @since 03.01.2022
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginProperties {

    int resourceId() default 0;

    int metricsId() default 0;

    boolean metrics() default false;

}
