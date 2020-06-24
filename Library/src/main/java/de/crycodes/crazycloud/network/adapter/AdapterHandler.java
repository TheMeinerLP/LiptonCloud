package de.crycodes.crazycloud.network.adapter;

import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.packet.Packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Coded By CryCodes
 * Class: AdapterHandler
 * Date : 29.05.2020
 * Time : 16:54
 * Project: Networking-Framework
 */

public class AdapterHandler implements AdapterHandlerInterface {

    private Map< Class<? extends Packet>, PacketHandlerAdapter> registeredadapters = new HashMap<>();

    @Override
    public void registerAdapter( Class<? extends Packet> packet,PacketHandlerAdapter adapterHandler) {
        this.registeredadapters.put(packet, adapterHandler);
    }

    @Override
    public void unregisterAdapter(PacketHandlerAdapter adapterHandler) {
        this.registeredadapters.remove(adapterHandler);
    }

    @Override
    public void handelAdapterHandler(NetworkChannel networkChannel, Packet packet) {
        List<PacketHandlerAdapter> handlers = new ArrayList<>();
        registeredadapters.forEach((packet1, packetHandlerAdapter) -> {
            handlers.add(packetHandlerAdapter);
        });
        for (PacketHandlerAdapter adapter : handlers){
            if (adapter.getNetworkChannel().equals(networkChannel))
                adapter.handel(packet);
        }
    }
}
