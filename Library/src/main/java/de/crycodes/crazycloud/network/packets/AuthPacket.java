package de.crycodes.crazycloud.network.packets;

import de.crycodes.crazycloud.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: WrapperKeyPacket
 * Date : 31.05.2020
 * Time : 13:05
 * Project: Networking-Framework
 */

public class AuthPacket extends Packet {

    private final String key;

    public AuthPacket(String key) {
        super("AUTH");
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
