package de.crycodes.de.spacebyter.networking;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: GlobalPacketHandler
 * Date : 27.06.2020
 * Time : 10:56
 * Project: LiptonCloud
 */

public class GlobalPacketHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (LiptonMaster.getInstance().getMasterConfig().isDebugMode())
            LiptonMaster.getInstance().getColouredConsoleProvider().info("NEW PACKET - " + packet.getClass().getSimpleName());
    }
}
