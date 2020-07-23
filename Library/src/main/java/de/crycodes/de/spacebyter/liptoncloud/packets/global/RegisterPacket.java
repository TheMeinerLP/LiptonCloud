package de.crycodes.de.spacebyter.liptoncloud.packets.global;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

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
    private final String key;

    public RegisterPacket(Meta meta, RegisterType registerType, String key, ReceiverType receiverType) {
        super("REGISTER", receiverType);
        this.registerType = registerType;
        this.meta = meta;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Meta getMeta() {
        return meta;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }
}
