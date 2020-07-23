package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: DebugPacket
 * Date : 26.06.2020
 * Time : 09:27
 * Project: LiptonCloud
 */

public class DebugPacket extends Packet {

    private final String Message;
    private final String wrapperID;

    public DebugPacket(String message, String wrapperID, ReceiverType receiverType) {
        super("DebugPacket", receiverType);
        Message = message;
        this.wrapperID = wrapperID;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public String getMessage() {
        return Message;
    }
}
