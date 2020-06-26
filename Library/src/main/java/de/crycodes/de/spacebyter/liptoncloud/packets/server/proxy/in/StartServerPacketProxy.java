package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StartServerPacket
 * Date : 26.06.2020
 * Time : 18:02
 * Project: LiptonCloud
 */

public class StartServerPacketProxy extends Packet {

    private final ServerMeta serverMeta;

    public StartServerPacketProxy(ServerMeta serverMeta) {
        super("StartServerPacketProxy");
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}