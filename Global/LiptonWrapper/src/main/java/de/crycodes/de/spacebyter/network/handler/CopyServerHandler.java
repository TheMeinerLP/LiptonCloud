package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.io.*;

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
            final CopyServerPacket copyServerPacket = (CopyServerPacket) packet;
            if (liptonWrapper.getWrapperConfig().getWrapperID().equalsIgnoreCase(copyServerPacket.getWrapperID())){
                String serverName = copyServerPacket.getServerName();
                String templateName = copyServerPacket.getServerGroupMeta().getGroupName().toUpperCase();

                String serverType = copyServerPacket.getDynamic() ? "dynamic" : "static";

                File serverDir = new File(serverLocation + "/" + serverType + "/" + copyServerPacket.getServerGroupMeta().getGroupName() + "/" + serverName);
                final File templateDir = new File(templateName + "/" + templateName);

                try {
                    copyFolder(serverDir, templateDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                liptonWrapper.getColouredConsoleProvider().info("Copied Server: '" + serverName + "' in Template of ServerGroup: '" + copyServerPacket.getServerGroupMeta().getGroupName() + "' !");

            } else {
                return;
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
    private void copyFolder(File src, File dest) throws IOException {
        if(src.isDirectory()){
            if(!dest.exists()){
                dest.mkdir();
            }

            String files[] = src.list();

            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyFolder(srcFile,destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }
    //</editor-fold>

}
