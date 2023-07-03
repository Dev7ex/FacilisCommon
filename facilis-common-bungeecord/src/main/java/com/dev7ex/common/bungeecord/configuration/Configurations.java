package com.dev7ex.common.bungeecord.configuration;

import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 25.07.2022
 */
public class Configurations {

    private Configurations() {}

    public static void addDefault(@NotNull final Configuration configuration, @NotNull final String path, @NotNull final Object value) {
        if (configuration.contains(path)) {
            return;
        }
        configuration.set(path, value);
    }

}
