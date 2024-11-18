package com.dev7ex.common.translation;

import com.dev7ex.common.io.file.configuration.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Dev7ex
 * @since 31.10.2024
 */
public interface TranslationProvider<E> {

    void register(@NotNull final Locale locale, @NotNull final File file);

    void unregister(@NotNull final Locale locale);

    String getMessage(@NotNull final E receiver, @NotNull final String path);

    List<String> getMessageList(@NotNull final E receiver, @NotNull final String path);

    Locale getDefaultLocale();

    Map<Locale, FileConfiguration> getTranslationConfigurations();

}
