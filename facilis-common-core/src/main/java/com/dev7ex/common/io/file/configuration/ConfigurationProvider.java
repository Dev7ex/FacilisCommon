package com.dev7ex.common.io.file.configuration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class ConfigurationProvider
{

    private static final Map<Class<? extends ConfigurationProvider>, ConfigurationProvider> providers = new HashMap<>();

    static
    {
        try
        {
            providers.put( YamlConfiguration.class, new YamlConfiguration() );
        } catch ( NoClassDefFoundError ex )
        {
            // Ignore, no SnakeYAML
        }

        try
        {
            providers.put( JsonConfiguration.class, new JsonConfiguration() );
        } catch ( NoClassDefFoundError ex )
        {
            // Ignore, no Gson
        }
    }

    public static ConfigurationProvider getProvider(Class<? extends ConfigurationProvider> provider)
    {
        return providers.get( provider );
    }

    /*------------------------------------------------------------------------*/
    public abstract void save(FileConfiguration config, File file) throws IOException;

    public abstract void save(FileConfiguration config, Writer writer);

    public abstract FileConfiguration load(File file) throws IOException;

    public abstract FileConfiguration load(File file, FileConfiguration defaults) throws IOException;

    public abstract FileConfiguration load(Reader reader);

    public abstract FileConfiguration load(Reader reader, FileConfiguration defaults);

    public abstract FileConfiguration load(InputStream is);

    public abstract FileConfiguration load(InputStream is, FileConfiguration defaults);

    public abstract FileConfiguration load(String string);

    public abstract FileConfiguration load(String string, FileConfiguration defaults);
}
