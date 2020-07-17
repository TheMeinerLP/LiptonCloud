package de.crycodes.de.spacebyter.proxy;

import de.crycodes.de.spacebyter.liptoncloud.utils.FileUtils;
import de.crycodes.de.spacebyter.liptoncloud.utils.TextTockens;

import java.io.*;
import java.net.URL;

/**
 * Coded By CryCodes
 * Class: BungeeCordFileManager
 * Date : 03.07.2020
 * Time : 14:27
 * Project: LiptonCloud
 */

public class BungeeCordFileManager {

    private final String bungeeurl = "https://ci.md-5.net/job/BungeeCord/lastSuccessfulBuild/artifact/bootstrap/target/BungeeCord.jar";

    //<editor-fold desc="BungeeCordFileManager">
    public BungeeCordFileManager() throws IOException {
        final File location = new File( "./liptonMaster/proxys/");
        final File pluginLocation = new File( "./liptonMaster/proxys/plugins/");
        final File bungeeCordFile = new File( "./liptonMaster/proxys/BungeeCord.jar");
        final FileWriter writer = new FileWriter(new File(location + "/config.yml"));
        final TextTockens textTockens = new TextTockens();
        writer.write(textTockens.bungeeconfigContent(25565));

        if (!pluginLocation.exists()) pluginLocation.mkdirs();

        FileUtils.copyFile(new File("./liptonMaster/api/LiptonBridge-1.0-SNAPSHOT.jar"), new File("./liptonMaster/proxys/plugins/LiptonBridge-1.0-SNAPSHOT.jar"));

        writer.flush();
        writer.close();
        if (!location.exists()) return;
        if (bungeeCordFile.exists()) return;
        getJar(bungeeurl, bungeeCordFile);
    }
    //</editor-fold>

    /**
     * Method to Download an Jar or other file
     *
     * @param Url | for Url Connection
     * */
    //<editor-fold desc="getJar">
    private void getJar(String Url, File location) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(Url).openStream());
             FileOutputStream fileOS = new FileOutputStream(location)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {

        }
    }
    //</editor-fold>

}