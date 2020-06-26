package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ProxyStoppingPacket
 * Date : 26.06.2020
 * Time : 08:53
 * Project: LiptonCloud
 */

public class ProxyStoppingPacket extends Packet {

    private final ProxyMeta proxyMeta;


    public ProxyStoppingPacket(ProxyMeta proxyMeta) {
        super("ProxyStoppingPacket");
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
