package com.dev7ex.common.bukkit.plugin.service;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
@Beta
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PluginServiceOrder {

    PluginServiceOption[] options() default {PluginServiceOption.COMMANDS, PluginServiceOption.LISTENERS, PluginServiceOption.SERVICES};

}
