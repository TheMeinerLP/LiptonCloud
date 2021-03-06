package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: ExecuteCommandPacket
 * Date : 26.06.2020
 * Time : 08:41
 * Project: LiptonCloud
 */

public class ExecuteCommandPacket extends Packet {

    private final String commadLine;
    private final String ServerName;

    public ExecuteCommandPacket(String commadLine, String serverName, ReceiverType receiverType) {
        super("ExecuteCommandPacket", receiverType);
        this.commadLine = commadLine;
        ServerName = serverName;
    }

    public String getCommadLine() {
        return commadLine;
    }

    public String getServerName() {
        return ServerName;
    }
}
