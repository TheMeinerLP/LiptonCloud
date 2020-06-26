package de.crycodes.de.spacebyter.liptoncloud.packets.global;

import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: RegisterResponsePacket
 * Date : 26.06.2020
 * Time : 13:50
 * Project: LiptonCloud
 */

public class RegisterResponsePacket extends Packet {

    private final boolean isAuthenticated;

    public RegisterResponsePacket(boolean isAuthenticated) {
        super("RegisterResponsePacket");
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
