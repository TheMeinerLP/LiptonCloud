package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

public class ServerUpdatePacket extends Packet {

    private final ServerMeta updatedServerMeta;

    public ServerUpdatePacket(ServerMeta updatedServerMeta) {
        super("ServerUpdatePacket");
        this.updatedServerMeta = updatedServerMeta;
    }

    public ServerMeta getUpdatedServerMeta() {
        return updatedServerMeta;
    }
}
