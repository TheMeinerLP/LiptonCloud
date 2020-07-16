package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ProxyGroupConfig
 * Date : 26.06.2020
 * Time : 08:09
 * Project: LiptonCloud
 */

public class ProxyGroupConfig {

    private ProxyMeta serverMeta;

    private Document document;
    private File configFile;


    //<editor-fold desc="Class methods">
    public void create(ProxyMeta serverGroupMeta){
        this.serverMeta = serverGroupMeta;
        this.configFile = new File("./liptonMaster/groups/proxy/" + serverMeta.getName() + ".json");
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document();
        document.append("group", this.serverMeta);

        document.saveAsConfig(configFile);
    }

    public ProxyMeta getProxyMetaByName(String name){
        if (getServerMetas().isEmpty()){
            ColouredConsoleProvider.getGlobal().error("No ServerGroups found!");
            return null;
        }
        for (ProxyMeta serverGroupMeta : getServerMetas()){
            if (serverGroupMeta.getName().equalsIgnoreCase(name)){
                return serverGroupMeta;
            }
        }
        ColouredConsoleProvider.getGlobal().error("ProxyGroup not found!");
        return null;
    }

    public List<ProxyMeta> getServerMetas(){
        File location = new File("./liptonMaster/groups/proxy/");
        List<ProxyMeta> serverMetas = new ArrayList<>();
        for (File config : location.listFiles()){
            Document document = Document.loadDocument(config);
            serverMetas.add(document.getObject("group", ProxyMeta.class));
        }
        return serverMetas;
    }

    public void reload(){
        this.serverMeta = document.getObject("group", ProxyMeta.class);
    }
    //</editor-fold>
}
