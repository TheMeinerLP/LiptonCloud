package de.crycodes.crazycloud.network.packets;

import de.crycodes.crazycloud.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthResponsePacket
 * Date : 31.05.2020
 * Time : 13:11
 * Project: Networking-Framework
 */

public class AuthResponsePacket extends Packet {

    private final boolean result;

    public AuthResponsePacket(Boolean isacapted) {
        super("AUTH-RESPONSE");
        this.result = isacapted;
    }

    public boolean getResult() {
        return result;
    }
}
