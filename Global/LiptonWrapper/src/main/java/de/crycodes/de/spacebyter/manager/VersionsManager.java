package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class VersionsManager {

    private WrapperConfig wrapperConfig;
    private SpigotVersions versions;
    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="VersionsManager">
    public VersionsManager(WrapperConfig wrapperConfig, LiptonWrapper liptonWrapper) {
        this.wrapperConfig = wrapperConfig;
        this.liptonWrapper = liptonWrapper;
        if(wrapperConfig.getSpigotVersion().equals("-")) {
            return;
        }
        versions = SpigotVersions.valueOf(wrapperConfig.getSpigotVersion());
        this.liptonWrapper.getColouredConsoleProvider().getLogger().info("This Cloud is Using the Version " + versions.getJarName().replace(".jar", ""));
    }
    //</editor-fold>

    //<editor-fold desc="install">
    public void install(SpigotVersions versions) {
        if(wrapperConfig.getSpigotVersion().equalsIgnoreCase(versions.name())) {
            liptonWrapper.getColouredConsoleProvider().getLogger().error("This version is already installed!");
            return;
        }

        liptonWrapper.getColouredConsoleProvider().getLogger().info("Trying to install Server Version: " + versions.name());
        liptonWrapper.getWrapperConfig().getDocument().append("spigotVersion", versions.name());
        liptonWrapper.getWrapperConfig().getDocument().saveAsConfig(liptonWrapper.getWrapperConfig().getConfigFile());

        File jarfile = new File("liptonWrapper/resources/" + versions.getJarName());
        liptonWrapper.getColouredConsoleProvider().getLogger().info("Downloading Version: " + versions + " please don't stop the Wrapper");

        //TIME
        liptonWrapper.getCounter().start();
        //TIME

        getJar(versions.getUrl(), jarfile);

        //TIME
        liptonWrapper.getCounter().stop();
        liptonWrapper.getCounter().printResult("VersionInstall", liptonWrapper.getColouredConsoleProvider());
        //TIME

        liptonWrapper.getColouredConsoleProvider().getLogger().info("This Cloud is Using Versions: " + versions.name());
        if (liptonWrapper.getWrapperMasterClient() != null)
            liptonWrapper.getWrapperMasterClient().sendPacket(new InfoPacket("The Wrapper: " + liptonWrapper.getWrapperConfig().getWrapperID() + " installed the Version: " + versions.name(), liptonWrapper.getWrapperConfig().getWrapperID(), ReceiverType.MASTER));
    }
    //</editor-fold>

    //<editor-fold desc="getJar">
    public void getJar(String Url, File location) {
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

    //<editor-fold desc="getCurrentServerVersion">
    public SpigotVersions getCurrentServerVersion(){
        return this.versions;
    }
    //</editor-fold>

}
