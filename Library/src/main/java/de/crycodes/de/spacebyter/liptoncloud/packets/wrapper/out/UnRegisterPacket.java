package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;
import de.crycodes.de.spacebyter.network.packet.Packet;

public class UnRegisterPacket extends Packet {

    private final RegisterType registerType;
    private final Meta meta;

    public UnRegisterPacket(RegisterType registerType, Meta meta) {
        super("UnRegisterPacket");
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
