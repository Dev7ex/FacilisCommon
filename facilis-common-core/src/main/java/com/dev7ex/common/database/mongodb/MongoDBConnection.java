package com.dev7ex.common.database.mongodb;

import com.dev7ex.common.database.DatabaseConnection;
import com.dev7ex.common.database.DatabaseProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.10.2024
 */
@Getter(AccessLevel.PUBLIC)
public class MongoDBConnection extends DatabaseConnection {

    private MongoClient client;
    private MongoDatabase database;

    public MongoDBConnection(@NotNull final DatabaseProperties properties) {
        super(properties);
    }

    @Override
    public void onConnect() {
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
                MongoClient.getDefaultCodecRegistry());

        final MongoClientOptions options = MongoClientOptions.builder()
                .codecRegistry(codecRegistry)
                .build();

        this.client = new MongoClient(new ServerAddress(super.properties.getHostName(), super.properties.getPort()),
                MongoCredential.createCredential(super.properties.getUserName(), super.properties.getDatabase(), super.properties.getPassword().toCharArray()), options);
        this.database = this.client.getDatabase(super.properties.getDatabase());
    }

    @Override
    public void onDisconnect() {
        if (this.client == null) {
            return;
        }
        this.client.close();
    }

    @Override
    public boolean isConnected() {
        return (this.client != null);
    }

}
