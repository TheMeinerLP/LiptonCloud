package de.crycodes.de.spacebyter.network.adapter;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

public abstract class PacketHandlerAdapter {

    private final NetworkChannel networkChannel;

    public PacketHandlerAdapter(NetworkChannel networkChannel) {
        this.networkChannel = networkChannel;
    }

    public void handel(Packet packet){

    }

    public NetworkChannel getNetworkChannel() {
        return networkChannel;
    }
}
