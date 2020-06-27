package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StartServerPacketProxy;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

/**
 * Coded By CryCodes
 * Class: StartServerHandler
 * Date : 26.06.2020
 * Time : 18:06
 * Project: LiptonCloud
 */

public class StartServerHandler extends PacketHandlerAdapter {


    @Override
    public void handel(Packet packet) {
        if (packet instanceof StartServerPacketProxy){
            final StartServerPacketProxy startServerPacketProxy = (StartServerPacketProxy) packet;
            final ServerMeta serverMeta = startServerPacketProxy.getServerMeta();

            ServerInfo info = ProxyServer.getInstance().constructServerInfo(serverMeta.getServerName(),
                    new InetSocketAddress("127.0.0.1", serverMeta.getPort()),
                    "Lipton-Service",
                    false);

            if (ProxyServer.getInstance().getServers().containsKey(serverMeta.getServerName())) return;
            ProxyServer.getInstance().getServers().put(serverMeta.getServerName(), info);
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase("Lobby"))
                ProxyServer.getInstance().getConfig().getListeners().iterator().next().getServerPriority().add(serverMeta.getServerName());
            //TODO: BROADCAST: SERVER WAS ADDED!
        }
    }
}