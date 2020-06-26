package de.crycodes.de.spacebyter.handler;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: ProxyFileConfig
 * Date : 26.06.2020
 * Time : 14:16
 * Project: LiptonCloud
 */

public class ProxyFileConfig {

    private String tablist_top;
    private String tablist_bottom;
    private String default_motd;
    private String maintenance_motd;
    private String maintenanceKickMessage;
    private String maintenanceVersionString;

    private Boolean useProxyConfig;
    private Integer maxPlayer;

    private File configFile = new File("./liptonMaster/proxyconfig.json");
    private Document document;

    public ProxyFileConfig() {
        if (!configFile.exists()){
            document = new Document();

            document.append("tablist_top", "");
            document.append("tablist_bottom", "");
            document.append("default_motd", "");
            document.append("maintenance_motd", "");
            document.append("maintenanceKickMessage", "");
            document.append("maintenanceVersionString", "");

            document.append("useProxyConfig", "");
            document.append("maxPlayer", "");

            document.saveAsConfig(configFile);
        } else {
            document = Document.loadDocument(configFile);
        }
    }
    public void load(){
        document = Document.loadDocument(configFile);
        this.tablist_top = document.getString("tablist_top");
        this.tablist_bottom = document.getString("tablist_bottom");
        this.default_motd = document.getString("default_motd");
        this.maintenance_motd = document.getString("maintenance_motd");
        this.maintenanceKickMessage = document.getString("maintenanceKickMessage");
        this.maintenanceVersionString = document.getString("maintenanceVersionString");

        this.useProxyConfig = document.getBoolean("useProxyConfig");
        this.maxPlayer = document.getInt("maxPlayer");
    }

    public String getTablist_top() {
        return tablist_top;
    }

    public String getTablist_bottom() {
        return tablist_bottom;
    }

    public String getDefault_motd() {
        return default_motd;
    }

    public String getMaintenance_motd() {
        return maintenance_motd;
    }

    public String getMaintenanceKickMessage() {
        return maintenanceKickMessage;
    }

    public String getMaintenanceVersionString() {
        return maintenanceVersionString;
    }

    public Boolean getUseProxyConfig() {
        return useProxyConfig;
    }

    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public File getConfigFile() {
        return configFile;
    }

    public Document getDocument() {
        return document;
    }
}
