package de.crycodes.de.spacebyter.serverhelper;

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

    private String server_start_message;
    private String server_stop_message;

    private Boolean useProxyConfig;
    private Boolean useNotify;
    private Integer maxPlayer;

    private File configFile = new File("./liptonMaster/proxyconfig.json");
    private Document document;

    //<editor-fold desc="ProxyFileConfig">
    public ProxyFileConfig() {
        if (!configFile.exists()){
            document = new Document();

            document.append("tablist_top", "\n§b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n§7Players: {PLAYERS}");
            document.append("tablist_bottom", "\n§7Server: {SERVER}");
            document.append("default_motd", "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bAvailable §7✸ §7We are available.");
            document.append("maintenance_motd", "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bMaintenance §7✸ §7We are in maintenance mode.");
            document.append("maintenanceKickMessage", "§bLipton Cloud\n§7We are in maintenance mode");
            document.append("maintenanceVersionString", "§7[§bWartungsmodus§7]");

            document.append("server_start_message", "The Server '{SERVER}' is starting on Wrapper '{WRAPPER}' with Group '{GROUP}'");
            document.append("server_stop_message", "The Server '{SERVER}' is stopping on Wrapper '{WRAPPER}'");
            document.append("useNotify", true);

            document.append("useProxyConfig", true);
            document.append("maxPlayer", 50);

            document.saveAsConfig(configFile);
        } else {
            load();
        }
    }
    //</editor-fold>

    //<editor-fold desc="load">
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

        this.useNotify = document.getBoolean("useNotify");
        this.server_start_message = document.getString("server_start_message");
        this.server_stop_message = document.getString("server_stop_message");
    }
    //</editor-fold>

    //<editor-fold desc="Getter - Setter">
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

    public String getServer_start_message() {
        return server_start_message;
    }

    public String getServer_stop_message() {
        return server_stop_message;
    }

    public Boolean getUseNotify() {
        return useNotify;
    }
    //</editor-fold>
}
