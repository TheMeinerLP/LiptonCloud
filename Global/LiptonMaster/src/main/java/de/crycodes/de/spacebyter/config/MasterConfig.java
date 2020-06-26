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
    private boolean colorConsole;
    private boolean autoUpdate;
    private boolean GC_GPU_Overheat;
    private String serverNameSplitter;
    private String[] disabledModule;

    private boolean maintenance;
    private String[] whiteListed;

    //DOCUMENT AND FILE
    private Document document;
    private File configFile = new File("./liptonMaster/config.json");

    public MasterConfig() {
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document("Cloud Config");
        document.append("host", "127.0.0.1");
        document.append("port", 9685);
        document.append("debugMode", false);
        document.append("colorConsole", true);
        document.append("autoUpdate", false);
        document.append("GC_CPU_Overheat", true);
        document.append("serverNameSplitter", "-");
        document.append("disabledModules", new String[]{""});
        document.append("maintenance", true);
        document.append("whitelistedUser", new String[]{""});

        document.saveAsConfig(configFile);

    }
    public void reload(){
        this.host = document.getString("host");
        this.port = document.getInt("port");
        this.debugMode = document.getBoolean("debugMode");
        this.colorConsole = document.getBoolean("colorConsole");
        this.autoUpdate = document.getBoolean("autoUpdate");
        this.GC_GPU_Overheat = document.getBoolean("GC_CPU_Overheat");
        this.serverNameSplitter = document.getString("serverNameSplitter");
        this.disabledModule = document.getObject("disabledModules", String[].class);
        this.maintenance = document.getBoolean("maintenance");
        this.whiteListed = document.getObject("whitelistedUser", String[].class);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public boolean isColorConsole() {
        return colorConsole;
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
}

