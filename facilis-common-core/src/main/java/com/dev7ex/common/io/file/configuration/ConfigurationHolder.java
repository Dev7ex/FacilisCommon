package com.dev7ex.common.io.file.configuration;

import java.io.File;

/**
 * A contract for objects that provide the data folder location.
 */
public interface ConfigurationHolder {

    /**
     * Gets the data folder.
     *
     * @return The data folder.
     */
    File getDataFolder();

}
