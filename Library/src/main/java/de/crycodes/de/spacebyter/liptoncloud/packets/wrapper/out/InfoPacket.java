package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: InfoPacket
 * Date : 26.06.2020
 * Time : 09:00
 * Project: LiptonCloud
 */

public class InfoPacket extends Packet {

    private final String message;
    private final String wrapperID;

    public InfoPacket(String message, String wrapperID) {
        super("InfoPacket");
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
