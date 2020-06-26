package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerPacket
 * Date : 26.06.2020
 * Time : 08:37
 * Project: LiptonCloud
 */

public class StopServerPacket extends Packet {

    private final String serverName;

    public StopServerPacket(String serverName) {
        super("StopServerPacket");
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }
}
