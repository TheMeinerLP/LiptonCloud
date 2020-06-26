package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out;

import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.network.packet.Packet;

public class UnRegisterPacket extends Packet {

    private final RegisterType registerType;

    public UnRegisterPacket(RegisterType registerType) {
        super("UnRegisterPacket");
        this.registerType = registerType;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }
}
