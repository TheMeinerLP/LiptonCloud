package de.crycodes.de.spacebyter.network.adapter;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

public interface AdapterHandlerInterface {

    void registerAdapter(PacketHandlerAdapter adapterHandler);
    void unregisterAdapter(PacketHandlerAdapter adapterHandler);

    void handelAdapterHandler(NetworkChannel networkChannel, Packet packet);
}
