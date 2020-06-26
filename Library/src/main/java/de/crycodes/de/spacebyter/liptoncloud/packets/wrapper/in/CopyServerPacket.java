package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: CopyServerPacket
 * Date : 26.06.2020
 * Time : 08:40
 * Project: LiptonCloud
 */

public class CopyServerPacket extends Packet {

    private final ServerGroupMeta serverGroupMeta;
    private final String serverName;
    private final String wrapperID;

    public CopyServerPacket(ServerGroupMeta serverGroupMeta, String serverName, String wrapperID) {
        super("CopyServerPacket");
        this.serverGroupMeta = serverGroupMeta;
        this.serverName = serverName;
        this.wrapperID = wrapperID;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public String getServerName() {
        return serverName;
    }
}