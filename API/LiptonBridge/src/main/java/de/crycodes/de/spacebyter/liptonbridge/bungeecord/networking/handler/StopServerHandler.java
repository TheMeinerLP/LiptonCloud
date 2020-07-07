package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopServerPacketProxy;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
        if (packet instanceof ServerStoppingPacket){
            final ServerStoppingPacket stopServerPacketProxy = (ServerStoppingPacket) packet;

            ProxyServer.getInstance().getServers().remove(stopServerPacketProxy.getServerMeta().getServerName());
            System.out.println("Stopping: " + stopServerPacketProxy.getServerMeta().getServerName());

            String stopMessage = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyConfig().getServer_stop_message().replace("{SERVER}", stopServerPacketProxy.getServerMeta().getServerName()).replace("{WRAPPER}", stopServerPacketProxy.getServerMeta().getWrapperID()).replace("{GROUP}", stopServerPacketProxy.getServerMeta().getServerGroupMeta().getGroupName());

            for (ProxiedPlayer current : ProxyServer.getInstance().getPlayers()){
                if (LiptonBungeeBridge.getInstance().getProxyConfig().getCloudAdminPlayers().contains(current.getName())){
                    current.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + stopMessage);
                }
            }

        }
    }
}
