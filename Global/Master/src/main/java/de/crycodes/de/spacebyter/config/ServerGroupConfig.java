package de.crycodes.de.spacebyter.config;

import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerGroupConfig {

    private ServerGroupMeta serverMeta;

    private Document document;
    private File configFile;


    public ServerGroupConfig(ServerGroupMeta serverMeta) {
        this.serverMeta = serverMeta;
        this.configFile = new File("./liptonMaster/groups/" + serverMeta.getGroupName() + ".json");
        if (configFile.exists()){
            document = Document.loadDocument(configFile);
            reload();
            return;
        }
        document = new Document();
        document.append("group", this.serverMeta);

        document.saveAsConfig(configFile);

    }

    public List<ServerGroupMeta> getServerMetas(){
        File location = new File("./liptonMaster/groups/");
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

}
