package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerGroupPacket
 * Date : 26.06.2020
 * Time : 08:37
 * Project: LiptonCloud
 */

public class StopServerGroupPacket extends Packet {

    private final ServerGroupMeta serverGroupMeta;
    private final Integer stopID;

    public StopServerGroupPacket(ServerGroupMeta serverGroupMeta, Integer stopID) {
        super("StopServerGroupPacket");
        this.serverGroupMeta = serverGroupMeta;
        this.stopID = stopID;
    }

    public Integer getStopID() {
        return stopID;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }
}
