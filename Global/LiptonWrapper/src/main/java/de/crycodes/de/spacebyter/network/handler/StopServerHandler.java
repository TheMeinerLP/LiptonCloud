package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.io.IOException;

/**
 * Coded By CryCodes
 * Class: StopServerHandler
 * Date : 28.06.2020
 * Time : 21:17
 * Project: LiptonCloud
 */

public class StopServerHandler extends PacketHandlerAdapter {

    private final LiptonWrapper liptonWrapper;

    public StopServerHandler(LiptonWrapper liptonWrapper) {
        this.liptonWrapper = liptonWrapper;
    }

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacket){
            final StopServerPacket stopServerPacket = (StopServerPacket) packet;

            try {
                liptonWrapper.getDeleteServerManager().delete(stopServerPacket.getServerName());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //</editor-fold>
}
