package com.dev7ex.common.bukkit.configuration;

import lombok.SneakyThrows;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Dev7ex
 * @since 10.07.2022
 */
public abstract class ConfigurationBase {

    private File configurationFile;
    private YamlConfiguration yamlConfiguration;
    private final Plugin plugin;

    @SneakyThrows
    public ConfigurationBase(final Plugin plugin) {
        if (!this.getClass().isAnnotationPresent(ConfigurationProperties.class)) {
            throw new UnsupportedOperationException(this.getClass().getSimpleName() + " is missing " + ConfigurationProperties.class);
        }
        this.plugin = plugin;

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdirs();
        }
        this.configurationFile = new File(plugin.getDataFolder() + File.separator + this.getFileName());
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.configurationFile);
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
        this.configurationFile = new File(this.plugin.getDataFolder(), this.getFileName());
        if (this.configurationFile.exists()) {
            return;
        }
        try (final InputStream inputStream = this.plugin.getClass().getClassLoader().getResourceAsStream(this.getFileName())) {
            Files.copy(inputStream, this.configurationFile.toPath());
        }
    }

    public void loadFile() {
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.configurationFile);
    }

    @SneakyThrows
    public void saveFile() {
        this.yamlConfiguration.save(this.configurationFile);
    }

    public final String getFileName() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).fileName();
    }

    public File getConfigurationFile() {
        return this.configurationFile;
    }

    public YamlConfiguration getFileConfiguration() {
        return this.yamlConfiguration;
    }

}
