package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopProxyPacket
 * Date : 26.06.2020
 * Time : 08:55
 * Project: LiptonCloud
 */

public class StopProxyPacket extends Packet {

    private final ProxyMeta proxyMeta;


    public StopProxyPacket(ProxyMeta proxyMeta) {
        super("StopProxyPacket");
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
