package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.io.IOException;

/**
 * Coded By CryCodes
 * Class: StartServerHandler
 * Date : 26.06.2020
 * Time : 17:18
 * Project: LiptonCloud
 */

public class StartServerHandler extends PacketHandlerAdapter {

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="StartServerHandler">
    public StartServerHandler(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof StartServerPacket){
            final StartServerPacket startServerPacket = (StartServerPacket) packet;
            if (liptonWrapper.getWrapperConfig().getWrapperID().equalsIgnoreCase(startServerPacket.getWrapperID())){
                if(liptonWrapper.getWrapperConfig().getSpigotVersion().equals("-")) {
                    liptonWrapper.getColouredConsoleProvider().getLogger().error("No Version Defined in Config use the install Command to install a Version");
                    return;
                }
                try {

                    liptonWrapper.getTemplateManager().checkTemplate(startServerPacket.getServerMeta().getServerGroupMeta());

                    liptonWrapper.getServerFileManager().createServer(startServerPacket.getServerMeta());

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                return;
            }
        }
    }
    //</editor-fold>
}
