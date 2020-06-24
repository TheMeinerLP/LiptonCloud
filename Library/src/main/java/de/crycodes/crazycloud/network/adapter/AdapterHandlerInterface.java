package de.crycodes.crazycloud.network.adapter;

import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.packet.Packet;

public interface AdapterHandlerInterface {

    void registerAdapter(Class<? extends Packet> packet, PacketHandlerAdapter adapterHandler);
    void unregisterAdapter(PacketHandlerAdapter adapterHandler);

    void handelAdapterHandler(NetworkChannel networkChannel, Packet packet);
}
