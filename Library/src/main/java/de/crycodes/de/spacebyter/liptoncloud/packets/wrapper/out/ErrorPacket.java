package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: ErrorPacket
 * Date : 26.06.2020
 * Time : 09:27
 * Project: LiptonCloud
 */

public class ErrorPacket extends Packet {

    private final String message;
    private final String wrapperID;
    private final Exception exception;

    public ErrorPacket(String message, String wrapperID, Exception exception, ReceiverType receiverType) {
        super("ErrorPacket", receiverType);
        this.message = message;
        this.wrapperID = wrapperID;
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public String getMessage() {
        return message;
    }
}
