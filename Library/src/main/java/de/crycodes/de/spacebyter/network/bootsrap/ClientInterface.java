package de.crycodes.de.spacebyter.network.bootsrap;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

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
