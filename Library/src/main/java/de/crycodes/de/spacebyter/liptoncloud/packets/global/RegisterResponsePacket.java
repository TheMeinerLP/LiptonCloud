package de.crycodes.de.spacebyter.liptoncloud.packets.global;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: RegisterResponsePacket
 * Date : 26.06.2020
 * Time : 13:50
 * Project: LiptonCloud
 */

public class RegisterResponsePacket extends Packet {

    private final boolean isAuthenticated;

    private final String message;

    public RegisterResponsePacket(boolean isAuthenticated, String message, ReceiverType receiverType) {
        super("RegisterResponsePacket", receiverType);
        this.isAuthenticated = isAuthenticated;
        this.message = message;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getMessage() {
        return message;
    }
}
