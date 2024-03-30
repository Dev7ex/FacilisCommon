package com.dev7ex.common.bukkit.plugin;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.BukkitCommandExecutor;
import com.dev7ex.common.bukkit.plugin.module.PluginModule;
import com.dev7ex.common.bukkit.plugin.module.PluginModuleManager;
import com.dev7ex.common.bukkit.plugin.statistic.PluginStatisticProperties;
import com.dev7ex.common.bukkit.util.ProtocolVersion;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Dev7ex
 * @since 02.03.2024
 */
@Getter(AccessLevel.PUBLIC)
public class BasePlugin extends JavaPlugin {

    private final PluginModuleManager moduleManager = new PluginModuleManager(this);
    private final ProtocolVersion protocolVersion = ProtocolVersion.getCurrentProtocolVersion();

    /**
     * Creates the data folder for the plugin if it does not exist.
     * The data folder is a directory where the plugin stores its configuration
     * files, data, or any other persistent information.
     * If the folder already exists, this method does nothing.
     * If the folder creation fails, an exception may be thrown.
     *
     * @author Dev7ex
     * @since 1.0.3
     */
    public void createDataFolder() {
        if (super.getDataFolder().exists()) {
            return;
        }
        super.getDataFolder().mkdirs();
    }

    /**
     * Creates a subfolder within the designated parent directory if it does not exist.
     * The subfolder is a directory that resides inside a specified parent directory.
     * If the subfolder already exists, this method does nothing.
     *
     * @author Dev7ex
     * @since 1.0.3
     */
    public void createSubFolder(@NotNull final String folderName) {
        final File subFolder = new File(this.getDataFolder().getPath() + File.separator + folderName);
        if (subFolder.exists()) {
            return;
        }
        subFolder.mkdirs();
    }

    /**
     * Retrieves a subfolder within the designated parent directory.
     * The subfolder is a directory that resides inside a specified parent directory.
     * If the subfolder does not exist, this method returns null.
     *
     * @author Dev7ex
     * @since 1.0.3
     */
    public File getSubFolder(@NotNull final String folderName) {
        return new File(this.getDataFolder().getPath() + File.separator + folderName);
    }

    public PluginCommand registerCommand(@NotNull final String command) {
        return super.getServer().getPluginCommand(command);
    }

    public void registerCommand(@NotNull final BukkitCommand bukkitCommand) {
        final PluginCommand pluginCommand = super.getCommand(bukkitCommand.getName());
        Objects.requireNonNull(pluginCommand).setExecutor(new BukkitCommandExecutor(bukkitCommand));

        if((bukkitCommand.getAliases()) != null && (bukkitCommand.getAliases().length > 0)) {
            pluginCommand.setAliases(Arrays.asList(bukkitCommand.getAliases()));
        }
        if (!(bukkitCommand instanceof TabCompleter)) {
            return;
        }
        pluginCommand.setTabCompleter((TabCompleter) bukkitCommand);
    }

    public void registerListener(@NotNull final Listener listener) {
        super.getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerListenerIf(@NotNull final Listener listener, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(true)) {
            super.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public void registerModule(@NotNull final PluginModule module) {
        this.moduleManager.registerModule(module);
    }

    public void registerModuleIf(@NotNull final PluginModule module, @NotNull final Predicate<Boolean> predicate) {
        if (predicate.test(false)) {
            return;
        }
        this.moduleManager.registerModule(module);
    }

    public boolean hasStatistics() {
        return this.getClass().isAnnotationPresent(PluginStatisticProperties.class);
    }

    public PluginStatisticProperties getStatisticProperties() {
        return this.getClass().getAnnotation(PluginStatisticProperties.class);
    }

    public PluginIdentification getPluginIdentification() {
        return this.getClass().getAnnotation(PluginIdentification.class);
    }

    public boolean hasDatabase() {
        return this.getClass().isAssignableFrom(DatabasePlugin.class);
    }

}
