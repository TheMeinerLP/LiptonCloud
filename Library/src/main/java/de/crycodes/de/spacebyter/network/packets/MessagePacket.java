package de.crycodes.de.spacebyter.network.packets;

import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: MessagePacket
 * Date : 31.05.2020
 * Time : 13:04
 * Project: Networking-Framework
 */

public class MessagePacket extends Packet {

    private final String message;

    public MessagePacket(String message) {
        super("MESSAGE");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
