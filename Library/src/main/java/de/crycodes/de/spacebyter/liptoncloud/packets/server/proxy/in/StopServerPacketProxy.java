package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerPacketProxy
 * Date : 26.06.2020
 * Time : 18:02
 * Project: LiptonCloud
 */

public class StopServerPacketProxy extends Packet {

    private final ServerMeta serverMeta;

    public StopServerPacketProxy(ServerMeta serverMeta) {
        super("StopServerPacketProxy");
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
