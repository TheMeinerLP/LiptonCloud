package de.crycodes.addon.signsystem.packets;

import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: UnRegisterSignPacket
 * Date : 25.06.2020
 * Time : 16:20
 * Project: LiptonCloud
 */

public class UnRegisterSignPacket extends Packet {
    public UnRegisterSignPacket(String id) {
        super(id);
    }
}
