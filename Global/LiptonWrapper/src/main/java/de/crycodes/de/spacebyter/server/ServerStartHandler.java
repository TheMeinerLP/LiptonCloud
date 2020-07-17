package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.screen.Screen;

import java.io.File;
import java.io.IOException;

public class ServerStartHandler {

    private final File serverLocation = new File("liptonWrapper/server/");
    private final File templateLocation = new File("liptonWrapper/templates/");

    private Thread thread;

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="ServerStartHandler">
    public ServerStartHandler(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="startServer">
    public void startServer(ServerMeta serverMeta){

        thread = new Thread(serverMeta.getServerName()) {
            @Override
            public synchronized void start() {
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
                                    "SPIGOT.JAR -nogui"
                            };

                    ProcessBuilder processBuilder = new ProcessBuilder(cmd).directory(serverDir);
                    Process process = processBuilder.start();

                    liptonWrapper.getScreenManager().registerScreen(new Screen(thread, process, serverDir, serverMeta.getServerName()), serverMeta.getServerName());

                    liptonWrapper.getColouredConsoleProvider().info("Server Startet: " + serverMeta.getServerName() + " on (§c" + serverMeta.getPort() +  "§r)");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
    }
    //</editor-fold>
}
