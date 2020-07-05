package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ServerStartHandler {

    private final File serverLocation = new File("liptonWrapper/server/");
    private final File templateLocation = new File("liptonWrapper/templates/");

    public void startServer(ServerMeta serverMeta){

        String serverType = serverMeta.getServerGroupMeta().isDynamicService() ? "dynamic" : "static";

        File serverDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName() + "/" + serverMeta.getServerName() + "/");


        if (!serverDir.exists()){
            return;
        }
        try {
            final String[] cmd = new String[]
                    {
                            "java",
                            "-jar",
                            "SPIGOT.JAR"
                    };

            ProcessBuilder processBuilder = new ProcessBuilder(cmd).directory(serverDir);
            Process process = processBuilder.start();
            LiptonWrapper.getInstance().getColouredConsoleProvider().info("Server Startet: " + serverMeta.getServerName() + " on (§c" + serverMeta.getPort() +  "§r)");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
