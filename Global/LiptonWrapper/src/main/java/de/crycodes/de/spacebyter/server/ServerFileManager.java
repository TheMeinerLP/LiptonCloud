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

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="ServerFileManager">
    public ServerFileManager(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="createServer">
    public void createServer(ServerMeta serverMeta) throws IOException {

        String serverType = serverMeta.getServerGroupMeta().isDynamicService() ? "dynamic" : "static";

        File serverGroupDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName());
        File serverDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName() + "/" + serverMeta.getServerName());
        final File templateDir = new File(templateLocation + "/" + serverMeta.getServerGroupMeta().getGroupName().toUpperCase() + "/" + serverMeta.getServerGroupMeta().getTemplate().toUpperCase() + "/");

        serverGroupDir.mkdirs();
        serverDir.mkdirs();

        FileUtils.copyDirectory(templateDir, serverDir);

        TextTockens textTockens = new TextTockens();

        try (FileInputStream stream = new FileInputStream(new File(serverDir + "/server.properties"))){
            Properties properties = new Properties();
            properties.load(stream);

            properties.setProperty("server-port", serverMeta.getPort() + "");
            properties.setProperty("server-ip", "127.0.0.1");
            properties.setProperty("max-players", 20 + "");
            properties.setProperty("server-name", serverMeta.getServerName());
            properties.setProperty("online-mode", "false");

            properties.save(new FileOutputStream(new File(serverDir + "/server.properties")), "Edit by Cloud");
        } catch (Exception exception){ }

        FileWriter eula = new FileWriter(new File(serverDir + "/eula.txt"));
        eula.write(textTockens.eulaShit());
        eula.flush();
        eula.close();


        Document document = new Document();

        document.append("NAME", serverMeta.getServerName());
        document.append("WRAPPER-ID", serverMeta.getWrapperID());
        document.append("GROUP", serverMeta.getServerGroupMeta().getGroupName());
        document.append("ID", serverMeta.getId());
        document.append("DYNAMIC", serverMeta.getServerGroupMeta().isDynamicService());
        document.append("MAINTENANCE", serverMeta.getServerGroupMeta().isMaintenance());

        document.saveAsConfig(new File(serverDir + "/META.json"));

        liptonWrapper.getServerStartHandler().startServer(serverMeta);

    }
    //</editor-fold>
}
