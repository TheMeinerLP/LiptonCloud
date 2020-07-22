package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.screen.Screen;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class ServerStartHandler implements Runnable {

    private final File serverLocation = new File("liptonWrapper/server/");

    private final LiptonWrapper liptonWrapper;
    private final ServerMeta serverMeta;

    //<editor-fold desc="ServerStartHandler">
    public ServerStartHandler(ServerMeta serverMeta,LiptonWrapper liptonWrapper) {
        this.serverMeta = serverMeta;
        this.liptonWrapper = liptonWrapper;

        liptonWrapper.getPool().submit(this);
    }
    //</editor-fold>

    //<editor-fold desc="startServer">
    @Override
    public void run() {
        String serverType = serverMeta.getServerGroupMeta().isDynamicService() ? "dynamic" : "static";

        File serverDir = new File(serverLocation + "/" + serverType + "/" + serverMeta.getServerGroupMeta().getGroupName() + "/" + serverMeta.getServerName() + "/");


        if (!serverDir.exists()){
            return;
        }
        try {
            final String[] cmd = new String[]
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

            ProcessBuilder processBuilder = new ProcessBuilder(cmd).directory(serverDir);
            Process process = processBuilder.start();

            liptonWrapper.getScreenManager().registerScreen(new Screen(Thread.currentThread(), process, serverDir, serverMeta.getServerName()), serverMeta.getServerName());

            liptonWrapper.getColouredConsoleProvider().getLogger().info("Server Startet: " + serverMeta.getServerName() + " on (§c" + serverMeta.getPort() +  "§r)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>
}
