package de.crycodes.de.spacebyter.network.adapter;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

public abstract class PacketHandlerAdapter {

    public abstract void handel(Packet packet);
}
