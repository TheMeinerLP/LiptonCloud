package de.crycodes.addon.signsystem.network.handler;

import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: UnRegisterSignPacketHandler
 * Date : 25.06.2020
 * Time : 19:05
 * Project: LiptonCloud
 */

public class UnRegisterSignPacketHandler extends PacketHandlerAdapter {

    public UnRegisterSignPacketHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        super.handel(packet);
    }
}