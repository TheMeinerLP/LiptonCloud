package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.TextTockens;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class ServerFileManager {

    private final File serverLocation = new File("liptonWrapper/server/");
    private final File templateLocation = new File("liptonWrapper/templates/");

    public void createServer(ServerMeta serverMeta) throws IOException {

        String serverType = serverMeta.getServerGroupMeta().isDynamicService() ? "dynamic" : "static";

        File serverGroupDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName());
        File serverDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName() + "/" + serverMeta.getServerName());
        File templateDir = new File(templateLocation + "/" + serverMeta.getServerGroupMeta().getGroupName());

        serverGroupDir.mkdirs();
        serverDir.mkdirs();

        FileUtils.copyDirectory(templateDir, serverDir);

        TextTockens textTockens = new TextTockens();

        FileWriter writer = new FileWriter(new File(serverDir + "/server.properties"));
        writer.write(textTockens.propertiesContent(serverMeta.getServerName(), serverMeta.getPort()));
        writer.flush();
        writer.close();

        FileWriter eula = new FileWriter(new File(serverDir + "/eula.txt"));
        eula.write(textTockens.eulaShit());
        eula.flush();
        eula.close();


        Document document = new Document();
        document.append("META", serverMeta);
        document.saveAsConfig(new File(serverDir + "/META.json"));

        LiptonWrapper.getInstance().getServerStartHandler().startServer(serverMeta);


    }
}
