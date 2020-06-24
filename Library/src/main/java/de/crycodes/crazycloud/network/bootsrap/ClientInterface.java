package de.crycodes.crazycloud.network.bootsrap;

import de.crycodes.crazycloud.network.ThunderServer;
import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ClientServerInterface
 * Date : 29.05.2020
 * Time : 16:35
 * Project: Networking-Framework
 */

public interface ClientInterface {

    void sendPacket(NetworkChannel networkChannel, Packet packet);
}
