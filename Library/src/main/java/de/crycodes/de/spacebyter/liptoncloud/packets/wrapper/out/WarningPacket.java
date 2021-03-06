package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: Warning
 * Date : 26.06.2020
 * Time : 09:27
 * Project: LiptonCloud
 */

public class WarningPacket extends Packet {

    private final String message;
    private final String wrapperID;

    public WarningPacket(String message, String wrapperID, ReceiverType receiverType) {
        super("WarningPacket", receiverType);
        this.message = message;
        this.wrapperID = wrapperID;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public String getMessage() {
        return message;
    }
}
