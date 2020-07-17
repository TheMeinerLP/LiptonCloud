package de.crycodes.de.spacebyter.liptoncloud.player.mongo;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;

import java.text.MessageFormat;

/**
 * Coded By CryCodes
 * Class: MongoDB
 * Date : 17.07.2020
 * Time : 12:33
 * Project: LiptonCloud
 */

public class MongoDB {

    private final String hostname;
    private final Integer port;

    public MongoDB(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public void connect(String username, String password) {
        if(username == null || password == null)
            return;
        mongoClient = MongoClients.create(MessageFormat.format("mongodb://{0}:{1}@{2}:{3}/{4}", username, password, hostname, port, mongoDatabase));
        mongoDatabase = mongoClient.getDatabase("LiptonCloud");
        mongoCollection = mongoDatabase.getCollection("CloudPlayer");
    }
    public void disconnect() {
        if(mongoClient != null)  mongoClient.close();
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }
}
