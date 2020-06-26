package de.crycodes.de.spacebyter.server;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.FileUtils;
import de.crycodes.de.spacebyter.liptoncloud.utils.TextTockens;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateManager {

    private final SpigotVersions version = LiptonWrapper.getInstance().getVersionsManager().getCurrenServerVersion();
    private File templateLocation;
    private File spigotLocation;

    public TemplateManager() {
        if(version == null) {
            LiptonWrapper.getInstance().getColouredConsoleProvider().error("No Server Version found. Can't create Template");
            LiptonWrapper.getInstance().getWrapperMasterClient().sendPacket(new ErrorPacket("Could not create Template" ,LiptonWrapper.getInstance().getWrapperConfig().getWrapperID(), new Exception("No Server Version found")));
            return;
        }
        templateLocation = new File("liptonWrapper/templates");
        spigotLocation = new File("liptonWrapper/resources");
    }

    public void checkTemplate(ServerGroupMeta serverGroupMeta) throws IOException {
        final File template = new File(templateLocation + serverGroupMeta.getGroupName());
        if (!template.exists())
            createTemplate(serverGroupMeta);
    }

    private void createTemplate(ServerGroupMeta serverGroupMeta) throws IOException {
        final File template = new File(templateLocation + "/" + serverGroupMeta.getGroupName().toUpperCase());
        final File pluginlocation = new File(template + "/plugins/");
        final File spigotLocationInTemplate = new File(template + "/SPIGOT.JAR");
        if (template.exists()) return;
        template.mkdirs();
        pluginlocation.mkdirs();

        FileUtils.copyFile(spigotLocation, spigotLocationInTemplate);
        FileWriter writer = new FileWriter(template + "/server.properties");
        writer.write("#By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).\n" +
                "#Thu Apr 16 11:47:52 CEST 2020\n" +
                "eula=true\n");
        writer.flush();
        writer.close();

        FileWriter propertiesWriter = new FileWriter(template + "/server.properties");
        TextTockens textTockens = new TextTockens();
        propertiesWriter.write(textTockens.propertiesContent());
        propertiesWriter.flush();
        propertiesWriter.close();
        LiptonWrapper.getInstance().getColouredConsoleProvider().info("New Template was created for Server-Group: " + serverGroupMeta.getGroupName());
    }

}
