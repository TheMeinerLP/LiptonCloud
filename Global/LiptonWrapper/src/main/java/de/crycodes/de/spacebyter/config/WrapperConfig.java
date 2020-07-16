package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;

public class WrapperConfig {

    //OBJECTS IN CONFIG
    private String host;
    private Integer port;
    private String wrapperID;
    private int startPort;
    private boolean autoUpdate;
    private boolean GC_GPU_Overheat;
    private boolean colorUse;
    private String spigotVersion;

    //DOCUMENT AND FILE
    private Document document;
    private File configFile = new File("./liptonWrapper/config.json");

    //<editor-fold desc="WrapperConfig">
    public WrapperConfig() {
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document("Cloud Config");
        document.append("host", "127.0.0.1");
        document.append("wrapperID", "-");
        document.append("port", "9685");
        document.append("startPort", 9685);
        document.append("autoUpdate", false);
        document.append("GC_CPU_Overheat", true);
        document.append("colorUse", true);
        document.append("spigotVersion", "-");
        document.saveAsConfig(configFile);

    }
    //</editor-fold>
    //<editor-fold desc="reload">
    public void reload(){
        this.host = document.getString("host");
        this.wrapperID = document.getString("wrapperID");
        this.startPort = document.getInt("startPort");
        this.port = document.getInt("port");
        this.autoUpdate = document.getBoolean("autoUpdate");
        this.GC_GPU_Overheat = document.getBoolean("GC_CPU_Overheat");
        this.colorUse = document.getBoolean("colorUse");
        this.spigotVersion = document.getString("spigotVersion");
    }
    //</editor-fold>

    //<editor-fold desc="geetter - setter">
    public String getHost() {
        return host;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public int getStartPort() {
        return startPort;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public boolean isGC_GPU_Overheat() {
        return GC_GPU_Overheat;
    }

    public boolean isColorUse() {
        return colorUse;
    }

    public Integer getPort() {
        return port;
    }

    public String getSpigotVersion() {
        return spigotVersion;
    }

    public Document getDocument() {
        return document;
    }

    public File getConfigFile() {
        return configFile;
    }
    //</editor-fold>
}
