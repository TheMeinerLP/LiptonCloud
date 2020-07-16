package de.crycodes.de.spacebyter.networking.proxy.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ProxyStoppingPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ProxyStoppingHandler
 * Date : 26.06.2020
 * Time : 16:40
 * Project: LiptonCloud
 */

public class ProxyStoppingHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ProxyStoppingHandler">
    public ProxyStoppingHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ProxyStoppingPacket){
            final ProxyStoppingPacket stoppingPacket = (ProxyStoppingPacket) packet;
            ProxyMeta proxyMeta = stoppingPacket.getProxyMeta();
            liptonMaster.getProxyManager().unregisterProxy(proxyMeta);
        }
    }
    //</editor-fold>
}
