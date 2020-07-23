package de.crycodes.de.spacebyter.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapperConfig {

    //OBJECTS IN CONFIG
    private String host;
    private Integer port;
    private String wrapperID;
    private int startPort;
    private boolean autoUpdate;
    private boolean GC_GPU_Overheat;
    private String spigotVersion;
    private boolean useProcessParameters;

    private final String[] defaultArguments = new String[]
            {
                    "java",
                    "-XX:+UseG1GC",
                    "-XX:MaxGCPauseMillis=50",
                    "-XX:-UseAdaptiveSizePolicy",
                    "-XX:CompileThreshold=100",
                    "-Dio.netty.leakDetectionLevel=DISABLED",
                    "-Djline.terminal=jline.UnsupportedTerminal",
                    "-Dfile.encoding=UTF-8",
                    "-Xmx512M",
                    "-jar",
                    "SPIGOT.JAR"
            };

    private boolean setupDone = false;

    //DOCUMENT AND FILE
    private Document document;
    private File configFile = new File("./liptonWrapper/config.json");

    //<editor-fold desc="WrapperConfig">
    public WrapperConfig(boolean create) {
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        if (create) {
            document = new Document("Cloud Config");
            document.append("host", "127.0.0.1");
            document.append("wrapperID", "-");
            document.append("port", "9685");
            document.append("startPort", 30000);
            document.append("autoUpdate", false);
            document.append("GC_CPU_Overheat", true);
            document.append("spigotVersion", "-");
            document.append("useProcessParameters", false);
            document.append("setupDone", "true");
            document.append("processParameters", defaultArguments);
            document.saveAsConfig(configFile);
        }

    }
    //</editor-fold>


    public List<String> getJavaArguments(){
        JsonArray jsonArgs = this.document.getArray("processParameters");

        Gson gson = new Gson();

        List<String> arguments = new ArrayList<>();

        for (JsonElement jsonObject : jsonArgs){
            arguments.add(gson.fromJson(jsonObject, String.class));
        }

        return arguments;

    }

    //<editor-fold desc="createFromSetup">
    public void createFromSetup(String wrapperID, String hostMaster){
        document = new Document("Cloud Config");
        document.append("host", hostMaster);
        document.append("wrapperID", wrapperID);
        document.append("port", "9685");
        document.append("startPort", 30000);
        document.append("autoUpdate", false);
        document.append("GC_CPU_Overheat", true);
        document.append("spigotVersion", "-");
        document.append("useProcessParameters", false);
        document.append("setupDone", "true");
        document.append("processParamters", defaultArguments);
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
        this.spigotVersion = document.getString("spigotVersion");
        this.setupDone = document.getBoolean("setupDone");
        this.useProcessParameters = document.getBoolean("useProcessParameters");
    }
    //</editor-fold>

    //<editor-fold desc="geetter - setter">
    public String getHost() {
        return host;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
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

    public boolean isSetupDone() {
        return setupDone;
    }

    public boolean isUsingProcessParameters() {
        return useProcessParameters;
    }

    //</editor-fold>
}
