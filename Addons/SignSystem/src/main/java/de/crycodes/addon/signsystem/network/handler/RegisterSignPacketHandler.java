package de.crycodes.addon.signsystem.network.handler;

import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: RegisterSignPacketHandler
 * Date : 25.06.2020
 * Time : 19:04
 * Project: LiptonCloud
 */

public class RegisterSignPacketHandler extends PacketHandlerAdapter {

    public RegisterSignPacketHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        super.handel(packet);
    }
}