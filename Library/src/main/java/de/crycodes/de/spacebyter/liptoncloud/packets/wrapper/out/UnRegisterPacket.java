package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

public class UnRegisterPacket extends Packet {

    private final RegisterType registerType;
    private final Meta meta;

    public UnRegisterPacket(RegisterType registerType, Meta meta, ReceiverType receiverType) {
        super("UnRegisterPacket", receiverType);
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
