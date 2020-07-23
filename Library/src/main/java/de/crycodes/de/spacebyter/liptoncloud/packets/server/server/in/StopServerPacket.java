package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: StopServerPacket
 * Date : 26.06.2020
 * Time : 08:37
 * Project: LiptonCloud
 */

public class StopServerPacket extends Packet {

    private final String serverName;
    private final String wrapperID;
    private final boolean dynamic;

    public StopServerPacket(String serverName, String wrapperID, boolean dynamic, ReceiverType receiverType) {
        super("StopServerPacket", receiverType);
        this.serverName = serverName;
        this.wrapperID = wrapperID;
        this.dynamic = dynamic;
    }

    public boolean getDynamic() {
        return dynamic;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public String getServerName() {
        return serverName;
    }
}
