package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

public class ServerGroupUpdatePacket extends Packet {

    private final ServerGroupMeta updatedServerGroupMeta;

    public ServerGroupUpdatePacket(ServerGroupMeta updatedServerGroupMeta) {
        super("ServerGroupUpdatePacket");
        this.updatedServerGroupMeta = updatedServerGroupMeta;
    }

    public ServerGroupMeta getUpdatedServerGroupMeta() {
        return updatedServerGroupMeta;
    }
}
