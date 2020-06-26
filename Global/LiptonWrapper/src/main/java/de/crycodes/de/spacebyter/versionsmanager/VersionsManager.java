package de.crycodes.de.spacebyter.versionsmanager;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class VersionsManager {

    private WrapperConfig wrapperConfig;
    private SpigotVersions versions;

    public VersionsManager(WrapperConfig wrapperConfig) {
        this.wrapperConfig = wrapperConfig;
        if(wrapperConfig.getSpigotVersion().equals("-")) {
            LiptonWrapper.getInstance().getColouredConsoleProvider().error("No Version Defined in Config use the install Command to install a Version");
            return;
        }
        versions = SpigotVersions.valueOf(wrapperConfig.getSpigotVersion());
        LiptonWrapper.getInstance().getColouredConsoleProvider().info("This Cloud is Using the Version " + versions.getJarName().replace(".jar", ""));
    }

    public void install(SpigotVersions versions) {
        if(wrapperConfig.getSpigotVersion().equalsIgnoreCase(versions.name())) {
            LiptonWrapper.getInstance().getColouredConsoleProvider().error("This version is already installed!");
            return;
        }

        LiptonWrapper.getInstance().getColouredConsoleProvider().info("Trying to install Server Version: " + versions.name());
        LiptonWrapper.getInstance().getWrapperConfig().getDocument().append("spigotVersion", versions.name());
        LiptonWrapper.getInstance().getWrapperConfig().getDocument().saveAsConfig(LiptonWrapper.getInstance().getWrapperConfig().getConfigFile());

        File jarfile = new File("liptonWrapper/resources/" + versions.getJarName());
        LiptonWrapper.getInstance().getColouredConsoleProvider().info("Downloading Version: " + versions + " please don't stop the Wrapper");

        getJar(versions.getUrl(), jarfile);

        LiptonWrapper.getInstance().getColouredConsoleProvider().info("This Cloud is Using Versions: " + versions.name());
        LiptonWrapper.getInstance().getWrapperMasterClient().sendPacket(new InfoPacket("The Wrapper installed the Version: " + versions.name(), LiptonWrapper.getInstance().getWrapperConfig().getWrapperID()));
    }

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

    public SpigotVersions getCurrenServerVersion(){
        return this.versions;
    }

}
