package com.dev7ex.common.io.file.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class YamlConfiguration extends ConfigurationProvider
{

    private final ThreadLocal<Yaml> yaml = new ThreadLocal<Yaml>()
    {
        @Override
        protected Yaml initialValue()
        {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle( DumperOptions.FlowStyle.BLOCK );

            Representer representer = new Representer( options )
            {
                {
                    representers.put( FileConfiguration.class, new Represent()
                    {
                        @Override
                        public Node representData(Object data)
                        {
                            return represent( ( (FileConfiguration) data ).self );
                        }
                    } );
                }
            };

            return new Yaml( new Constructor( new LoaderOptions() ), representer, options );
        }
    };

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
        yaml.get().dump( config.self, writer );
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
        Map<String, Object> map = yaml.get().loadAs( reader, LinkedHashMap.class );
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
    @SuppressWarnings("unchecked")
    public FileConfiguration load(InputStream is, FileConfiguration defaults)
    {
        Map<String, Object> map = yaml.get().loadAs( is, LinkedHashMap.class );
        if ( map == null )
        {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration( map, defaults );
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
        Map<String, Object> map = yaml.get().loadAs( string, LinkedHashMap.class );
        if ( map == null )
        {
            map = new LinkedHashMap<>();
        }
        return new FileConfiguration( map, defaults );
    }
}
