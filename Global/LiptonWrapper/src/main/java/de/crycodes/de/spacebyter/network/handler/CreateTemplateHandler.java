package de.crycodes.de.spacebyter.network.handler;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CreateTemplatePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: CreateTemplateHandler
 * Date : 26.06.2020
 * Time : 17:17
 * Project: LiptonCloud
 */

public class CreateTemplateHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof CreateTemplatePacket){
            final CreateTemplatePacket templatePacket = (CreateTemplatePacket) packet;
            if (LiptonWrapper.getInstance().getWrapperConfig().getWrapperID().equalsIgnoreCase(templatePacket.getWrapperID())){
                LiptonWrapper.getInstance().getTemplateManager().checkTemplate(templatePacket.getServerGroupMeta());
            } else {
                return;
            }
        }
    }
}
