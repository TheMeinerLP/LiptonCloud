package de.crycodes.de.spacebyter.liptoncloud.packets;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: RegisterPacket
 * Date : 25.06.2020
 * Time : 20:39
 * Project: LiptonCloud
 */

public class RegisterPacket extends Packet {

    private final Meta meta;
    private final RegisterType registerType;

    public RegisterPacket(Meta meta, RegisterType registerType) {
        super("REGISTER");
        this.registerType = registerType;
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }
}
