package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthServerPacket
 * Date : 26.06.2020
 * Time : 08:48
 * Project: LiptonCloud
 */

public class AuthServerPacket extends Packet {

    private final String serverName;
    private final ServerMeta serverMeta;

    public AuthServerPacket(String serverName, ServerMeta serverMeta) {
        super("AuthServerPacket");
        this.serverName = serverName;
        this.serverMeta = serverMeta;
    }

    public String getServerName() {
        return serverName;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
