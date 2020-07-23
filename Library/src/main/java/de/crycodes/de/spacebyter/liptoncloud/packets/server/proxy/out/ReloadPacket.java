package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: ReloadPacket
 * Date : 26.06.2020
 * Time : 15:54
 * Project: LiptonCloud
 */

public class ReloadPacket extends Packet {

    public ReloadPacket(ReceiverType receiverType) {
        super("ReloadPacket", receiverType);
    }
}
