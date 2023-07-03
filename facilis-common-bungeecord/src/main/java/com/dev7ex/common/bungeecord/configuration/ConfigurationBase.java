package com.dev7ex.common.bungeecord.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Dev7ex
 * @since 19.07.2022
 */
@Getter(AccessLevel.PUBLIC)
public abstract class ConfigurationBase {

    private File configurationFile;
    private Configuration fileConfiguration;
    private final Plugin plugin;

    @SneakyThrows
    public ConfigurationBase(@NotNull final Plugin plugin) {
        if (!this.getClass().isAnnotationPresent(ConfigurationProperties.class)) {
            throw new UnsupportedOperationException(this.getClass().getSimpleName() + " is missing " + ConfigurationProperties.class);
        }
        this.plugin = plugin;

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdirs();
        }
        this.configurationFile = new File(plugin.getDataFolder() + File.separator + this.getFileName());
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

    @SneakyThrows
    public void loadFile() {
        this.fileConfiguration = ConfigurationProvider.getProvider(this.getProviderClass()).load(this.configurationFile, this.fileConfiguration);
    }

    @SneakyThrows
    public void saveFile() {
        ConfigurationProvider.getProvider(this.getProviderClass()).save(this.fileConfiguration, this.configurationFile);
    }

    public File getConfigurationFile() {
        return this.configurationFile;
    }

    public Configuration getFileConfiguration() {
        return this.fileConfiguration;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public final String getFileName() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).fileName();
    }

    public final Class<? extends ConfigurationProvider> getProviderClass() {
        return this.getClass().getAnnotation(ConfigurationProperties.class).provider();
    }

}
