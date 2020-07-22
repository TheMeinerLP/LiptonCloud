package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: MasterConfig
 * Date : 24.06.2020
 * Time : 11:14
 * Project: LiptonCloud
 */

public class MasterConfig {

    //OBJECTS IN CONFIG
    private String host;
    private int port;
    private boolean debugMode;
    private boolean autoUpdate;
    private boolean GC_GPU_Overheat;
    private String serverNameSplitter;
    private String[] disabledModule;

    private boolean maintenance;
    private String[] whiteListed;

    //DOCUMENT AND FILE
    private Document document;
    private File configFile = new File("./liptonMaster/config.json");

    //<editor-fold desc="MasterConfig - Class Methods">
    public MasterConfig() {
        if (configFile.exists()){
            reload();
            return;
        }
        document = new Document("Cloud Config");
        document.append("host", "127.0.0.1");
        document.append("port", 9685);
        document.append("debugMode", false);
        document.append("autoUpdate", false);
        document.append("GC_CPU_Overheat", true);
        document.append("serverNameSplitter", "-");
        document.append("disabledModules", new String[]{""});
        document.append("maintenance", true);
        document.append("whitelistedUser", new String[]{""});

        document.saveAsConfig(configFile);

    }
    public void reload(){
        document = Document.loadDocument(configFile);
        this.host = document.getString("host");
        this.port = document.getInt("port");
        this.debugMode = document.getBoolean("debugMode");
        this.autoUpdate = document.getBoolean("autoUpdate");
        this.GC_GPU_Overheat = document.getBoolean("GC_CPU_Overheat");
        this.serverNameSplitter = document.getString("serverNameSplitter");
        this.disabledModule = document.getObject("disabledModules", String[].class);
        this.maintenance = document.getBoolean("maintenance");
        this.whiteListed = document.getObject("whitelistedUser", String[].class);
    }
    public boolean switchMaintenance(){
        document = Document.loadDocument(configFile);
        if (this.maintenance){
            document.append("maintenance", false);

            document.saveAsConfig(configFile);
            return false;
        } else {
            document.append("maintenance", true);

            document.saveAsConfig(configFile);
            return true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="getter - setter">
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public boolean isGC_GPU_Overheat() {
        return GC_GPU_Overheat;
    }

    public String getServerNameSplitter() {
        return serverNameSplitter;
    }

    public String[] getDisabledModule() {
        return disabledModule;
    }

    public Document getDocument() {
        return document;
    }

    public File getConfigFile() {
        return configFile;
    }

    public String[] getWhiteListed() {
        return whiteListed;
    }

    public boolean isMaintenance() {
        return maintenance;
    }
    //</editor-fold>

}

