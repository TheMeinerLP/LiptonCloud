package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: StartServerPacket
 * Date : 26.06.2020
 * Time : 08:37
 * Project: LiptonCloud
 */

public class StartServerPacket extends Packet {

    private final String wrapperID;
    private final ServerMeta serverMeta;

    public StartServerPacket(String wrapperID, ServerMeta serverMeta, ReceiverType receiverType) {
        super("StartServerPacket", receiverType);
        this.wrapperID = wrapperID;
        this.serverMeta = serverMeta;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
