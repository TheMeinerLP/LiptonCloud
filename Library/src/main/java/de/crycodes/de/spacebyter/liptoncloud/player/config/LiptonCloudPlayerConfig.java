package de.crycodes.de.spacebyter.liptoncloud.player.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: LiptonCloudPlayerConfig
 * Date : 17.07.2020
 * Time : 13:00
 * Project: LiptonCloud
 */

public class LiptonCloudPlayerConfig {

    private final File config;

    private boolean useSystem;
    private boolean mysql;

    private String sqlPassword;
    private String sqlUserName;
    private String sqlHost;
    private Integer sqlPort;

    private String mongoPassword;
    private String mongoUserName;
    private String mongoHost;
    private Integer mongoPort;

    private Document document;

    public LiptonCloudPlayerConfig(File config) {
        this.config = config;

        if (config.exists()){
            reload();
            return;
        }

        document = new Document("PLAYER-CONFIG");

        document.append("useSystem", false);
        document.append("mysql", false);

        document.append("sqlPassword", "password");
        document.append("sqlUserName", "root");
        document.append("sqlHost", "127.0.0.1");
        document.append("sqlPort", "3306");

        document.append("mongoPassword", "password");
        document.append("mongoUserName", "admin");
        document.append("mongoHost", "127.0.0.1");
        document.append("mongoPort", "27017");

        document.saveAsConfig(config);
    }

    public void reload(){
        document = Document.loadDocument(config);

        this.useSystem = document.getBoolean("useSystem");
        this.mysql = document.getBoolean("mysql");

        this.sqlHost = document.getString("sqlHost");
        this.sqlPassword = document.getString("sqlPassword");
        this.sqlUserName = document.getString("sqlUserName");
        this.sqlPort = document.getInt("sqlPort");

        this.mongoHost = document.getString("mongoHost");
        this.mongoPassword = document.getString("mongoPassword");
        this.mongoPort = document.getInt("mongoPort");
        this.mongoUserName = document.getString("mongoUserName");
    }
}
