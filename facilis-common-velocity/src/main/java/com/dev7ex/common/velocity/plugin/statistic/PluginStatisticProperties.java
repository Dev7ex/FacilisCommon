package com.dev7ex.common.velocity.plugin.statistic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies properties for plugin statistics.
 * This annotation is used to mark classes that represent plugin statistics and specify their properties.
 * It allows specifying whether the statistic is enabled and providing an identification number.
 *
 * @author Dev7ex
 * @since 02.03.2024
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginStatisticProperties {

    /**
     * Specifies whether the statistic is enabled.
     *
     * @return true if the statistic is enabled, false otherwise.
     */
    boolean enabled() default false;

    /**
     * Specifies the identification number for the statistic.
     *
     * @return The identification number of the statistic.
     */
    int identification() default 0;

}
