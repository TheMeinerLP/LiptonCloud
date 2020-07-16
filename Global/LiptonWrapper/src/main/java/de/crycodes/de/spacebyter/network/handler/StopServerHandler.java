package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerHandler
 * Date : 28.06.2020
 * Time : 21:17
 * Project: LiptonCloud
 */

public class StopServerHandler extends PacketHandlerAdapter {

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacket){
            final StopServerPacket stopServerPacket = (StopServerPacket) packet;
            System.out.println("STOPPED: " + stopServerPacket.getServerName());
            System.out.println("WRAPPERID: " + stopServerPacket.getWrapperID());
            System.out.println("DYNAMIC: " + stopServerPacket.getDynamic());
        }
    }
    //</editor-fold>
}
