package com.dev7ex.common.io.file.configuration;

import com.google.gson.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class JsonConfiguration extends ConfigurationProvider
{

    private final Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting().registerTypeAdapter( FileConfiguration.class, new JsonSerializer<FileConfiguration>()
    {
        @Override
        public JsonElement serialize(FileConfiguration src, Type typeOfSrc, JsonSerializationContext context)
        {
            return context.serialize( ( (FileConfiguration) src ).self );
        }
    } ).create();

    @Override
    public void save(FileConfiguration config, File file) throws IOException
    {
        try ( Writer writer = new OutputStreamWriter( new FileOutputStream( file ), StandardCharsets.UTF_8 ) )
        {
            save( config, writer );
        }
    }

    @Override
    public void save(FileConfiguration config, Writer writer)
    {
        json.toJson( config.self, writer );
    }

    @Override
    public FileConfiguration load(File file) throws IOException
    {
        return load( file, null );
    }

    @Override
    public FileConfiguration load(File file, FileConfiguration defaults) throws IOException
    {
        try ( FileInputStream is = new FileInputStream( file ) )
        {
            return load( is, defaults );
        }
    }

    @Override
    public FileConfiguration load(Reader reader)
    {
        return load( reader, null );
    }

    @Override
    @SuppressWarnings("unchecked")
    public FileConfiguration load(Reader reader, FileConfiguration defaults)
    {
        Map<String, Object> map = json.fromJson( reader, LinkedHashMap.class );
        if ( map == null )
        {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration( map, defaults );
    }

    @Override
    public FileConfiguration load(InputStream is)
    {
        return load( is, null );
    }

    @Override
    public FileConfiguration load(InputStream is, FileConfiguration defaults)
    {
        return load( new InputStreamReader( is, StandardCharsets.UTF_8 ), defaults );
    }

    @Override
    public FileConfiguration load(String string)
    {
        return load( string, null );
    }

    @Override
    @SuppressWarnings("unchecked")
    public FileConfiguration load(String string, FileConfiguration defaults)
    {
        Map<String, Object> map = json.fromJson( string, LinkedHashMap.class );
        if ( map == null )
        {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration( map, defaults );
    }
}
