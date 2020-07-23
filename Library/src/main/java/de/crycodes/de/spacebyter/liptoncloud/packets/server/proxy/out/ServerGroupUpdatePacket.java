package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: ServerGroupUpdatePacket
 * Date : 26.06.2020
 * Time : 17:02
 * Project: LiptonCloud
 */

public class ServerGroupUpdatePacket extends Packet {

    private final ServerGroupMeta updatedServerGroupMeta;

    public ServerGroupUpdatePacket(ServerGroupMeta updatedServerGroupMeta, ReceiverType receiverType) {
        super("ServerGroupUpdatePacket", receiverType);
        this.updatedServerGroupMeta = updatedServerGroupMeta;
    }

    public ServerGroupMeta getUpdatedServerGroupMeta() {
        return updatedServerGroupMeta;
    }
}