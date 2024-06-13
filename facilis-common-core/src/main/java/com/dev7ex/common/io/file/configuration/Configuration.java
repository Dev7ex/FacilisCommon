package com.dev7ex.common.io.file.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * A utility class to manage file-based configurations.
 */
@Getter(AccessLevel.PUBLIC)
public class Configuration {

    private File configurationFile;
    private FileConfiguration fileConfiguration;
    private final ConfigurationHolder configurationHolder;

    /**
     * Constructs a new Configuration instance.
     *
     * @param configurationHolder The ConfigurationHolder providing data folder information.
     */
    @SneakyThrows
    public Configuration(@NotNull final ConfigurationHolder configurationHolder) {
        this.configurationHolder = configurationHolder;

        if (!this.configurationHolder.getDataFolder().exists()) {
            this.configurationHolder.getDataFolder().mkdirs();
        }
        this.configurationFile = new File(this.configurationHolder.getDataFolder() + File.separator + this.getFileName());
    }

    /**
     * Creates a new configuration file if it does not exist.
     */
    @SneakyThrows
    public void createFile() {
        this.configurationFile.createNewFile();
    }

    /**
     * Deletes the configuration file.
     */
    public void deleteFile() {
        this.configurationFile.delete();
    }

    /**
     * Copies the default configuration file from resources to the data folder if it does not exist.
     */
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

    /**
     * Loads the configuration file.
     */
    @SneakyThrows
    public void loadFile() {
        this.fileConfiguration = ConfigurationProvider.getProvider(this.getProvider()).load(this.configurationFile);
    }

    /**
     * Saves the configuration file.
     */
    @SneakyThrows
    public void saveFile() {
        ConfigurationProvider.getProvider(this.getProvider()).save(this.fileConfiguration, this.configurationFile);
    }

    /**
     * Gets the file name of the configuration.
     *
     * @return The file name.
     */
    public final String getFileName() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).fileName();
    }

    /**
     * Gets the configuration provider class.
     *
     * @return The configuration provider class.
     */
    public final Class<? extends ConfigurationProvider> getProvider() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).provider();
    }

}
