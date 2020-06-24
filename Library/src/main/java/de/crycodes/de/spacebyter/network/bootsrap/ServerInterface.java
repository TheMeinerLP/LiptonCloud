package de.crycodes.de.spacebyter.network.bootsrap;

import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

public interface ServerInterface {

    void sendPacket(NetworkChannel networkChannel, Packet packet);
}
