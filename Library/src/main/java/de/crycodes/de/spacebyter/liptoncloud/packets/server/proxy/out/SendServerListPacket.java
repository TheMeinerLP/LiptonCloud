package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.util.List;

/**
 * Coded By CryCodes
 * Class: SendServerListPacket
 * Date : 26.06.2020
 * Time : 08:47
 * Project: LiptonCloud
 */

public class SendServerListPacket extends Packet {

    private final List<ServerMeta> serverList;

    public SendServerListPacket(List<ServerMeta> serverList) {
        super("SendServerListPacket");
        this.serverList = serverList;
    }

    public List<ServerMeta> getServerList() {
        return serverList;
    }
}
