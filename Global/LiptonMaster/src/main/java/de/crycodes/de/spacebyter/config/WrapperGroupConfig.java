package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: WrapperGroupConfig
 * Date : 26.06.2020
 * Time : 08:23
 * Project: LiptonCloud
 */

public class WrapperGroupConfig {

    private final LiptonMaster liptonMaster;

    private WrapperMeta serverMeta;

    private Document document;
    private File configFile;


    public WrapperGroupConfig(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }

    //<editor-fold desc="Class Methods">
    public void create(WrapperMeta serverGroupMeta){
        this.serverMeta = serverGroupMeta;
        this.configFile = new File("./liptonMaster/groups/wrapper/" + serverMeta.getWrapperConfig().getWrapperId() + ".json");
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document();
        document.append("group", this.serverMeta);

        document.saveAsConfig(configFile);
    }

    public WrapperMeta getWrapperByID(String name){
        if (getWrapperMetas().isEmpty()){
            liptonMaster.getCloudConsole().getLogger().error("No ServerGroups found!");
            return null;
        }
        for (WrapperMeta serverGroupMeta : getWrapperMetas()){
            if (serverGroupMeta.getWrapperConfig().getWrapperId().equalsIgnoreCase(name)){
                return serverGroupMeta;
            }
        }
        liptonMaster.getCloudConsole().getLogger().error("ServerGroup not found!");
        return null;
    }

    public List<WrapperMeta> getWrapperMetas(){
        File location = new File("./liptonMaster/groups/wrapper/");
        List<WrapperMeta> serverMetas = new ArrayList<>();
        for (File config : location.listFiles()){
            Document document = Document.loadDocument(config);
            serverMetas.add(document.getObject("group", WrapperMeta.class));
        }
        return serverMetas;
    }

    public void reload(){
        this.serverMeta = document.getObject("group", WrapperMeta.class);
    }
    //</editor-fold>

}
