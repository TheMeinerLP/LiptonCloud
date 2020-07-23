package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: StartServerPacket
 * Date : 26.06.2020
 * Time : 18:02
 * Project: LiptonCloud
 */

public class StartServerPacketProxy extends Packet {

    private final ServerMeta serverMeta;

    public StartServerPacketProxy(ServerMeta serverMeta, ReceiverType receiverType) {
        super("StartServerPacketProxy", receiverType);
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}