package de.crycodes.de.spacebyter.liptoncloud.packets.global;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: ShutDownPacket
 * Date : 25.06.2020
 * Time : 20:46
 * Project: LiptonCloud
 */

public class ShutDownPacket extends Packet {

    private final int status;

    public ShutDownPacket(int status, ReceiverType receiverType) {
        super("SHUTDOWN", receiverType);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
