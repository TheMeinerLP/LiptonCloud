package de.crycodes.de.spacebyter.networking.server.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ServerUpdatePacket
 * Date : 26.06.2020
 * Time : 16:47
 * Project: LiptonCloud
 */

public class ServerUpdateHandler extends PacketHandlerAdapter {

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ServerUpdatePacket){
            final ServerUpdatePacket serverUpdatePacket = (ServerUpdatePacket) packet;
        }
    }
    //</editor-fold>
}
