package de.crycodes.de.spacebyter.liptoncloud.player;

import de.crycodes.de.spacebyter.liptoncloud.player.config.LiptonCloudPlayerConfig;
import de.crycodes.de.spacebyter.liptoncloud.player.meta.LiptonPlayer;
import de.crycodes.de.spacebyter.liptoncloud.player.mongo.MongoDB;
import de.crycodes.de.spacebyter.liptoncloud.player.mongo.MongoPlayer;
import de.crycodes.de.spacebyter.liptoncloud.player.mysql.SQLDB;
import de.crycodes.de.spacebyter.liptoncloud.player.mysql.SQLPlayer;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: LiptonPlayerManager
 * Date : 17.07.2020
 * Time : 12:36
 * Project: LiptonCloud
 */

public class LiptonPlayerManager {

    private final File configFile;

    private LiptonCloudPlayerConfig liptonCloudPlayerConfig;

    private SQLDB mySQL;
    private MongoDB mongoDB;

    public LiptonPlayerManager(File configFile) {
        this.configFile = configFile;

        liptonCloudPlayerConfig = new LiptonCloudPlayerConfig(configFile);

    }

    public void update(LiptonPlayer liptonPlayer){
        if (liptonCloudPlayerConfig.isMysql()){
            SQLPlayer sqlPlayer = new SQLPlayer(liptonPlayer, mySQL);
            sqlPlayer.updateLastLogin(System.currentTimeMillis());
            sqlPlayer.updateName(liptonPlayer.getName());
            return;
        }
        MongoPlayer mongoPlayer = new MongoPlayer(liptonPlayer, this.mongoDB.getMongoCollection());
        mongoPlayer.connectPlayer(document -> {});
    }

    public void createPlayer(LiptonPlayer liptonPlayer){
        if (liptonCloudPlayerConfig.isMysql()){
            SQLPlayer sqlPlayer = new SQLPlayer(liptonPlayer, mySQL);
            sqlPlayer.addEntry();
            return;
        }
        MongoPlayer mongoPlayer = new MongoPlayer(liptonPlayer, this.mongoDB.getMongoCollection());
        mongoPlayer.connectPlayer(document -> {});
    }


    public void connect(){
        if (liptonCloudPlayerConfig.isMysql()){
            mySQL = new SQLDB(liptonCloudPlayerConfig.getSqlHost(),liptonCloudPlayerConfig.getSqlPort(), liptonCloudPlayerConfig.getSqlUserName(), liptonCloudPlayerConfig.getSqlPassword());
            return;
        }
        mongoDB = new MongoDB(liptonCloudPlayerConfig.getMongoHost(), liptonCloudPlayerConfig.getMongoPort());
        mongoDB.connect(liptonCloudPlayerConfig.getMongoUserName(), liptonCloudPlayerConfig.getMongoPassword());
    }
    public void disconnect(){
        if (liptonCloudPlayerConfig.isMysql()){
            this.mySQL.disconnect();
            return;
        }
        mongoDB.disconnect();
    }

}
