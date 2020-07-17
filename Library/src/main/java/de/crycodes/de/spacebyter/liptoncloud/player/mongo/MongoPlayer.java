package de.crycodes.de.spacebyter.liptoncloud.player.mongo;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.model.Filters;

import de.crycodes.de.spacebyter.liptoncloud.player.meta.LiptonPlayer;
import org.bson.Document;

import java.util.function.Consumer;


/**
 * Coded By CryCodes
 * Class: MongoPlayer
 * Date : 17.07.2020
 * Time : 12:37
 * Project: LiptonCloud
 */

@Deprecated
public class MongoPlayer {


    private LiptonPlayer playerMeta;
    private MongoCollection<Document> mongoCollection;

    public MongoPlayer(LiptonPlayer playerMeta, MongoCollection<Document> mongoCollection) {
        this.playerMeta = playerMeta;
        this.mongoCollection = mongoCollection;
    }

    public void connectPlayer(Consumer<Document> consumer) {
        mongoCollection.find(Filters.eq("UUID", playerMeta.getUuid())).first((document, throwable) -> {

            if(document == null) {
                Document create = new Document("uuid", playerMeta.getUuid()).append("name", playerMeta.getName())
                        .append("first_login", playerMeta.getFirst_login()).append("last_login", playerMeta.getLast_login());


                mongoCollection.insertOne(create, (result, throwable1) -> {

                    if(throwable1 != null)
                        throwable1.printStackTrace();

                    if(result != null)
                        consumer.accept(create);
                });

                return;
            }
            if(!document.getString("name").equalsIgnoreCase(playerMeta.getName()))
                document.replace("name", playerMeta.getName());
            document.replace("last_login", System.currentTimeMillis());

            mongoCollection.replaceOne(Filters.eq("UUID", playerMeta.getUuid()), document, (result, throwable1) -> {

                if(throwable1 != null)
                    throwable1.printStackTrace();

                if(result != null)
                    consumer.accept(document);
            });
        });
    }

}