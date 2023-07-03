package com.dev7ex.common.bungeecord.configuration;

import net.md_5.bungee.config.ConfigurationProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigurationProperties {

    String fileName();

    Class<? extends ConfigurationProvider> provider();

}
