package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.DebugPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.WarningPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: MessagePacket
 * Date : 26.06.2020
 * Time : 16:35
 * Project: LiptonCloud
 */

public class MessageHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="MessageHandler">
    public MessageHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ErrorPacket){
            final ErrorPacket errorPacket = (ErrorPacket) packet;
            if (errorPacket.getException() == null)
                liptonMaster.getCloudConsole().getLogger().error(errorPacket.getMessage());
            else
                liptonMaster.getCloudConsole().getLogger().error(errorPacket.getMessage());
        }
        if (packet instanceof InfoPacket){
            final InfoPacket infoPacket = (InfoPacket) packet;
            liptonMaster.getCloudConsole().getLogger().info(infoPacket.getMessage());
        }
        if (packet instanceof WarningPacket){
            final WarningPacket warningPacket = (WarningPacket) packet;
            liptonMaster.getCloudConsole().getLogger().warning(warningPacket.getMessage());
        }
        if (packet instanceof DebugPacket){
            final DebugPacket debugPacket = (DebugPacket) packet;
            liptonMaster.getCloudConsole().getLogger().debug(debugPacket.getMessage());
        }
    }
    //</editor-fold>
}
