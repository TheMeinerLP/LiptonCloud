package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: StopServerPacketProxy
 * Date : 26.06.2020
 * Time : 18:02
 * Project: LiptonCloud
 */

public class StopServerPacketProxy extends Packet {

    private final ServerMeta serverMeta;

    public StopServerPacketProxy(ServerMeta serverMeta, ReceiverType receiverType) {
        super("StopServerPacketProxy", receiverType);
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
