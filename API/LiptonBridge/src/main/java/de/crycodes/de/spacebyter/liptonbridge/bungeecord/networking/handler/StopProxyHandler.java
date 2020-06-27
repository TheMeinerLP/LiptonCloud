package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopProxyHandler
 * Date : 26.06.2020
 * Time : 15:21
 * Project: LiptonCloud
 */

public class StopProxyHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopProxyPacket){
            final StopProxyPacket stopProxyPacket = (StopProxyPacket) packet;
            if (stopProxyPacket.getProxyMeta().getName().equalsIgnoreCase(LiptonBungeeBridge.getInstance().getCloudAPI().getProxyMeta().getName())
            && stopProxyPacket.getProxyMeta().getId().equals(LiptonBungeeBridge.getInstance().getCloudAPI().getProxyMeta().getId())){
                System.exit(ExitUtil.TERMINATED);
            }
        }
    }
}
