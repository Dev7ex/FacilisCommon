package com.dev7ex.common.io.file.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dev7ex
 * @since 15.01.2024
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigurationProperties {

    /**
     * Returns the file name.
     * This method returns the name of the file, including the file extension.
     *
     * @return The file name as a string.
     */
    String fileName();

    /**
     * Retrieves the configuration provider.
     * This method is responsible for obtaining and returning the configuration provider
     * used to manage and access application configuration settings.
     *
     * @return The configuration provider instance.
     */
    Class<? extends ConfigurationProvider> provider();

}
