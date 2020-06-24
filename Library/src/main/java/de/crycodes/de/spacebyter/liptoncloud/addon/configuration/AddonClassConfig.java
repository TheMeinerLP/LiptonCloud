package de.crycodes.de.spacebyter.liptoncloud.addon.configuration;

import java.io.File;
import java.io.Serializable;

public class AddonClassConfig implements Serializable {

    /**
     * The file of the config
     */
    private File file;

    /**
     * The name, version and main class of the addon
     */
    private String name;

    /**
     * The version of the addon
     */
    private String version;

    /**
     * The main class of the addon
     */
    private String main;

    @java.beans.ConstructorProperties({"file", "name", "version", "main"})
    public AddonClassConfig(File file, String name, String version, String main) {
        this.file = file;
        this.name = name;
        this.version = version;
        this.main = main;
    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public String getMain() {
        return this.main;
    }
}
