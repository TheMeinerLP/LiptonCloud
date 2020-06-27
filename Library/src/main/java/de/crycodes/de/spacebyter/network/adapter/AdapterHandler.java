package de.crycodes.de.spacebyter.network.adapter;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

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

    private ArrayList<PacketHandlerAdapter> registeredadapters = new ArrayList<>();

    @Override
    public void registerAdapter(PacketHandlerAdapter adapterHandler) {
        this.registeredadapters.add(adapterHandler);
    }

    @Override
    public void unregisterAdapter(PacketHandlerAdapter adapterHandler) {
        this.registeredadapters.remove(adapterHandler);
    }

    @Override
    public void handelAdapterHandler(NetworkChannel networkChannel, Packet packet) {

        for (PacketHandlerAdapter adapter : registeredadapters){
            adapter.handel(packet);
        }
    }
}
