package de.crycodes.de.spacebyter.proxy;

import de.crycodes.de.spacebyter.LiptonMaster;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Coded By CryCodes
 * Class: BungeeStartUpHandler
 * Date : 03.07.2020
 * Time : 14:32
 * Project: LiptonCloud
 */

public class BungeeStartUpHandler extends Thread {

    private final List<Process> proxyProcesses = new CopyOnWriteArrayList<>();
    private final File bungeeCordFile = new File("./liptonMaster/proxys/");
    private final String[] cmd = new String[]{"java", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=50", "-XX:-UseAdaptiveSizePolicy", "-XX:CompileThreshold=100", "-Dio.netty.leakDetectionLevel=DISABLED", "-Djline.terminal=jline.UnsupportedTerminal", "-Dfile.encoding=UTF-8", "-Xmx512M", "-jar", "BungeeCord.jar"};

    public BungeeStartUpHandler() throws IOException {
        new BungeeCordFileManager();
    }

    @Override
    public synchronized void start() {
        try {
            final ProcessBuilder processBuilder = new ProcessBuilder(cmd).directory(bungeeCordFile);
            final Process process = processBuilder.start();
            LiptonMaster.getInstance().getColouredConsoleProvider().info("Started BungeeCord Server on: §c25565 §r!");
            if (!process.isAlive())
                process.destroyForcibly();

        } catch (Exception e){
            e.printStackTrace();
        }
        super.start();
    }

    public void stopProxy() {
        proxyProcesses.forEach(Process::destroy);
    }

}