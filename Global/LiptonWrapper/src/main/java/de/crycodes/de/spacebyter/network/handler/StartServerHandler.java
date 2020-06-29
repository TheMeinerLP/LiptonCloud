package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
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

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StartServerPacket){
            final StartServerPacket startServerPacket = (StartServerPacket) packet;
            if (LiptonWrapper.getInstance().getWrapperConfig().getWrapperID().equalsIgnoreCase(startServerPacket.getWrapperID())){

                try {

                    LiptonWrapper.getInstance().getTemplateManager().checkTemplate(startServerPacket.getServerMeta().getServerGroupMeta());

                    LiptonWrapper.getInstance().getServerFileManager().createServer(startServerPacket.getServerMeta());

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                return;
            }
        }
    }
}
