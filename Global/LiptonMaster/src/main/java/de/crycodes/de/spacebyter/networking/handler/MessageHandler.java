package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.DebugPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.WarningPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: MessagePacket
 * Date : 26.06.2020
 * Time : 16:35
 * Project: LiptonCloud
 */

public class MessageHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof ErrorPacket){
            final ErrorPacket errorPacket = (ErrorPacket) packet;
            if (errorPacket.getException() == null)
                LiptonMaster.getInstance().getColouredConsoleProvider().error(errorPacket.getMessage());
            else
                LiptonMaster.getInstance().getColouredConsoleProvider().error(errorPacket.getMessage(), errorPacket.getException());
        }
        if (packet instanceof InfoPacket){
            final InfoPacket infoPacket = (InfoPacket) packet;
            LiptonMaster.getInstance().getColouredConsoleProvider().info(infoPacket.getMessage());
        }
        if (packet instanceof WarningPacket){
            final WarningPacket warningPacket = (WarningPacket) packet;
            LiptonMaster.getInstance().getColouredConsoleProvider().warning(warningPacket.getMessage());
        }
        if (packet instanceof DebugPacket){
            final DebugPacket debugPacket = (DebugPacket) packet;
            LiptonMaster.getInstance().getColouredConsoleProvider().debug(debugPacket.getMessage());
        }
    }
}
