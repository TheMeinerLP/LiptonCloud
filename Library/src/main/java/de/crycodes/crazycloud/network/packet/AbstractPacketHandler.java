package de.crycodes.crazycloud.network.packet;

import de.crycodes.crazycloud.network.ThunderClient;
import de.crycodes.crazycloud.network.ThunderServer;
import de.crycodes.crazycloud.network.bootsrap.ClientInterface;
import de.crycodes.crazycloud.network.bootsrap.ServerInterface;
import de.crycodes.crazycloud.network.channel.NetworkChannel;

/**
 * Coded By CryCodes
 * Class: AbstractPacketHandler
 * Date : 29.05.2020
 * Time : 16:03
 * Project: Networking-Framework
 */

public interface AbstractPacketHandler {

    void registerPacket(Byte id, Class<? extends Packet> packet);
    void unregisterPacket(Byte id);
    void sendPacket(NetworkChannel networkChannel, ServerInterface server, Packet packet);
    void sendPacket(NetworkChannel networkChannel, ClientInterface client, Packet packet);

}
