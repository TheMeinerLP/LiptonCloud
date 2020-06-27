package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopServerPacketProxy;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import net.md_5.bungee.api.ProxyServer;

/**
 * Coded By CryCodes
 * Class: StopSerrverHandler
 * Date : 26.06.2020
 * Time : 18:06
 * Project: LiptonCloud
 */

public class StopServerHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacket){
            final StopServerPacket stopServerPacketProxy = (StopServerPacket) packet;


            //TODO: BROADCAST: SERVER WAS REMOVED!

            String stopMessage = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyConfig().getServer_stop_message();

        }
    }
}
