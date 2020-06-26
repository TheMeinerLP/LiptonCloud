package de.crycodes.de.spacebyter.network.packet;

import de.crycodes.de.spacebyter.network.bootsrap.ClientInterface;
import de.crycodes.de.spacebyter.network.bootsrap.ServerInterface;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * Coded By CryCodes
 * Class: PacketRunner
 * Date : 29.05.2020
 * Time : 16:03
 * Project: Networking-Framework
 */

public class PacketHandler implements AbstractPacketHandler {

    private Map<Byte, Class<? extends Packet>> registerdpackets = new HashMap<>();

    @Override
    public void registerPacket(Byte id, Class<? extends Packet> packet) {
        this.registerdpackets.put(id,packet);
    }

    @Override
    public void unregisterPacket(Byte id) {
        this.registerdpackets.remove(id);
    }

    @Override
    public void sendPacket(NetworkChannel networkChannel, ServerInterface server, Packet packet) {
        server.sendPacket(networkChannel,packet);
    }

    @Override
    public void sendPacket(NetworkChannel networkChannel, ClientInterface client, Packet packet) {
        client.sendPacket(networkChannel,packet);
    }

    public Map<Byte, Class<? extends Packet>> getRegisterdpackets() {
        return registerdpackets;
    }
}
