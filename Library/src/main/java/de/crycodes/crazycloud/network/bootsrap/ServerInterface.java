package de.crycodes.crazycloud.network.bootsrap;

import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.packet.Packet;

public interface ServerInterface {

    void sendPacket(NetworkChannel networkChannel, Packet packet);
}
