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

    private SpigotVersions version;
    private File templateLocation;
    private File spigotLocation;
    
    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="TemplateManager">
    public TemplateManager(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
        version = liptonWrapper.getVersionsManager().getCurrentServerVersion();
        if(version == null) {
            liptonWrapper.getColouredConsoleProvider().error("No Server Version found. Can't create Template");
            liptonWrapper.getWrapperMasterClient().sendPacket(new ErrorPacket("Could not create Template" ,liptonWrapper.getWrapperConfig().getWrapperID(), new Exception("No Server Version found")));
            return;
        }
        templateLocation = new File("liptonWrapper/templates");
        spigotLocation = new File("liptonWrapper/resources");
    }
    //</editor-fold>

    //<editor-fold desc="checkTemplate">
    public void checkTemplate(ServerGroupMeta serverGroupMeta) {
        final File template = new File(templateLocation + serverGroupMeta.getGroupName().toUpperCase() , serverGroupMeta.getTemplate().toUpperCase() + "/");
        if (!template.exists()) {
            try {
                createTemplate(serverGroupMeta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="createTemplate">
    private void createTemplate(ServerGroupMeta serverGroupMeta) throws IOException {
        final File templateGroup = new File(templateLocation + "/" + serverGroupMeta.getGroupName().toUpperCase() + "/" );
        final File template = new File(templateGroup + "/" + serverGroupMeta.getTemplate().toUpperCase() + "/");
        final File pluginlocation = new File(template + "/plugins/");
        final File spigotLocationInTemplate = new File(template + "/SPIGOT.JAR");
        final TextTockens textTockens = new TextTockens();
        if (template.exists()) return;

        templateGroup.mkdirs();
        template.mkdirs();
        pluginlocation.mkdirs();

        FileUtils.copyFile(new File("./liptonWrapper/api/LiptopnBridge-1.0-SNAPSHOT.jar"), new File(pluginlocation + "/LiptopnBridge-1.0-SNAPSHOT.jar"));

        FileUtils.copyFile(new File(spigotLocation + "/" + version.getJarName()), spigotLocationInTemplate);

        FileWriter propertiesWriter = new FileWriter(template + "/server.properties");
        propertiesWriter.write(textTockens.propertiesContent());
        propertiesWriter.flush();
        propertiesWriter.close();

        liptonWrapper.getColouredConsoleProvider().info("New Template was created for Server-Group: " + serverGroupMeta.getGroupName());
    }
    //</editor-fold>

}
