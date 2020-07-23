package de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in;

import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: SendProxyConfigPacket
 * Date : 26.06.2020
 * Time : 08:48
 * Project: LiptonCloud
 */

public class SendProxyConfigPacket extends Packet {

    private final ProxyConfig proxyConfig;
    private final String proxyName;

    public SendProxyConfigPacket(ProxyConfig proxyConfig, String proxyName, ReceiverType receiverType) {
        super("SendProxyConfigPacket", receiverType);
        this.proxyConfig = proxyConfig;
        this.proxyName = proxyName;
    }

    public String getProxyName() {
        return proxyName;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }
}
