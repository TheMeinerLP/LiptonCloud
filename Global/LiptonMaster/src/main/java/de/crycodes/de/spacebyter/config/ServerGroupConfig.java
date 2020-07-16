package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerGroupConfig {

    private ServerGroupMeta serverMeta;

    private Document document;
    private File configFile;


    //<editor-fold desc="Class Methods">
    public void create(ServerGroupMeta serverGroupMeta){
        this.serverMeta = serverGroupMeta;
        this.configFile = new File("./liptonMaster/groups/server/" + serverMeta.getGroupName() + ".json");
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document();
        document.append("group", this.serverMeta);

        document.saveAsConfig(configFile);
    }
    public void replace(ServerGroupMeta serverMeta){
        this.serverMeta = serverMeta;
        this.configFile = new File("./liptonMaster/groups/server/" + serverMeta.getGroupName() + ".json");

        document = new Document();
        document.append("group", this.serverMeta);

        document.saveAsConfig(configFile);
    }

    public ServerGroupMeta getServerMetaByName(String name){
        if (getServerMetas().isEmpty()){
            ColouredConsoleProvider.getGlobal().error("No ServerGroups found!");
            return null;
        }
        for (ServerGroupMeta serverGroupMeta : getServerMetas()){
            if (serverGroupMeta.getGroupName().equalsIgnoreCase(name)){
                return serverGroupMeta;
            }
        }
        ColouredConsoleProvider.getGlobal().error("ServerGroup not found!");
        return null;
    }

    public List<ServerGroupMeta> getServerMetas(){
        File location = new File("./liptonMaster/groups/server/");
        List<ServerGroupMeta> serverMetas = new ArrayList<>();
        for (File config : location.listFiles()){
            Document document = Document.loadDocument(config);
            serverMetas.add(document.getObject("group", ServerGroupMeta.class));
        }
        return serverMetas;
    }

    public void reload(){
        this.serverMeta = document.getObject("group", ServerGroupMeta.class);
    }
    //</editor-fold>

}
