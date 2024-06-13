package com.dev7ex.common.io.file.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of ConfigurationProvider for JSON-based configurations.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class JsonConfiguration extends ConfigurationProvider {

    private final Gson json = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .registerTypeAdapter(FileConfiguration.class, (JsonSerializer<FileConfiguration>) (configuration, type, context)
                    -> context.serialize(configuration.self)).create();

    @Override
    public void save(final FileConfiguration config, final File file) throws IOException {
        try (final Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            this.save(config, writer);
        }
    }

    @Override
    public void save(final FileConfiguration config, final Writer writer) {
        this.json.toJson(config.self, writer);
    }

    @Override
    public FileConfiguration load(final File file) throws IOException {
        return this.load(file, null);
    }

    @Override
    public FileConfiguration load(final File file, final FileConfiguration defaults) throws IOException {
        try (final FileInputStream is = new FileInputStream(file)) {
            return this.load(is, defaults);
        }
    }

    @Override
    public FileConfiguration load(final Reader reader) {
        return this.load(reader, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public FileConfiguration load(final Reader reader, final FileConfiguration defaults) {
        Map<String, Object> map = this.json.fromJson(reader, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration(map, defaults);
    }

    @Override
    public FileConfiguration load(final InputStream is) {
        return this.load(is, null);
    }

    @Override
    public FileConfiguration load(final InputStream is, final FileConfiguration defaults) {
        return this.load(new InputStreamReader(is, StandardCharsets.UTF_8), defaults);
    }

    @Override
    public FileConfiguration load(final String string) {
        return this.load(string, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public FileConfiguration load(final String string, final FileConfiguration defaults) {
        Map<String, Object> map = this.json.fromJson(string, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration(map, defaults);
    }
}
