package com.dev7ex.common.io.file.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@Getter(AccessLevel.PUBLIC)
public class Configuration {

    private File configurationFile;
    private FileConfiguration fileConfiguration;
    private final ConfigurationHolder configurationHolder;

    @SneakyThrows
    public Configuration(@NotNull final ConfigurationHolder configurationHolder) {
        this.configurationHolder = configurationHolder;

        if (!this.configurationHolder.getDataFolder().exists()) {
            this.configurationHolder.getDataFolder().mkdirs();
        }
        this.configurationFile = new File(this.configurationHolder.getDataFolder() + File.separator + this.getFileName());
    }

    @SneakyThrows
    public void createFile() {
        this.configurationFile.createNewFile();
    }

    public void deleteFile() {
        this.configurationFile.delete();
    }

    @SneakyThrows
    public void copyFile() {
        this.configurationFile = new File(this.configurationHolder.getDataFolder(), this.getFileName());
        if (this.configurationFile.exists()) {
            return;
        }
        try (final InputStream inputStream = this.configurationHolder.getClass().getClassLoader().getResourceAsStream(this.getFileName())) {
            Files.copy(inputStream, this.configurationFile.toPath());
        }
    }

    @SneakyThrows
    public void loadFile() {
        this.fileConfiguration = ConfigurationProvider.getProvider(this.getProvider()).load(this.configurationFile);
    }

    @SneakyThrows
    public void saveFile() {
        ConfigurationProvider.getProvider(this.getProvider()).save(this.fileConfiguration, this.configurationFile);
    }

    public final String getFileName() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).fileName();
    }

    public final Class<? extends ConfigurationProvider> getProvider() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).provider();
    }

}
