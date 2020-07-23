package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: StopProxyPacket
 * Date : 26.06.2020
 * Time : 08:55
 * Project: LiptonCloud
 */

public class StopProxyPacket extends Packet {

    private final ProxyMeta proxyMeta;


    public StopProxyPacket(ProxyMeta proxyMeta, ReceiverType receiverType) {
        super("StopProxyPacket", receiverType);
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
