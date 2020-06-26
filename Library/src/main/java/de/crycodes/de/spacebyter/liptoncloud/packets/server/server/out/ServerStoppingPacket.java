package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ServerStopingPacket
 * Date : 26.06.2020
 * Time : 08:40
 * Project: LiptonCloud
 */

public class ServerStoppingPacket extends Packet {

    private final ServerMeta serverMeta;

    public ServerStoppingPacket(ServerMeta serverMeta) {
        super("ServerStoppingPacket");
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
