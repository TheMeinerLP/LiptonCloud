package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;

/**
 * Coded By CryCodes
 * Class: CopyServerHandler
 * Date : 26.06.2020
 * Time : 17:17
 * Project: LiptonCloud
 */

public class CopyServerHandler extends PacketHandlerAdapter {

    private final File serverLocation = new File("liptonWrapper/server/");
    private final File templateLocation = new File("liptonWrapper/templates/");

    private final LiptonWrapper liptonWrapper;

    public CopyServerHandler(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof CopyServerPacket){
            CopyServerPacket copyServerPacket = (CopyServerPacket) packet;
            if (liptonWrapper.getWrapperConfig().getWrapperID().equalsIgnoreCase(copyServerPacket.getWrapperID())){
                String serverName = copyServerPacket.getServerName();
                String templateName = copyServerPacket.getServerGroupMeta().getGroupName().toUpperCase();

                String serverType = copyServerPacket.getDynamic() ? "dynamic" : "static";

                File serverDir = new File(serverLocation + File.separator + serverType + File.separator + copyServerPacket.getServerGroupMeta().getGroupName() + File.separator + serverName);
                File templateDir = new File(templateName + File.separator + templateName);
                copyFolder(serverDir, templateDir);
                liptonWrapper.getColouredConsoleProvider().getLogger().getLogger().log(Level.INFO,"Copied Server: '{}' in Template of ServerGroup: '{}' !", new Object[]{serverName,copyServerPacket.getServerGroupMeta().getGroupName()});

            }
        }
    }
    //</editor-fold>

    
    /**
     * Method to Copy a Folder
     *
     * @param src | Folder from
     * @param dest | Folder to
     * */
    //<editor-fold desc="copyFolder">
    private void copyFolder(File src, File dest) {
        try {
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            this.liptonWrapper.getLoggerProvider().getLogger().log(Level.SEVERE,"Something is wrong on copy a folder", e);
        }
    }
    //</editor-fold>

}
