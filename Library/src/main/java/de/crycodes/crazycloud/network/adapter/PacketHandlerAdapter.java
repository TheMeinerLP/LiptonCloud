package de.crycodes.crazycloud.network.adapter;

import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.packet.Packet;

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
