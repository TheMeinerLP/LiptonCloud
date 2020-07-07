package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: SendProxyConfigHandler
 * Date : 26.06.2020
 * Time : 15:21
 * Project: LiptonCloud
 */

public class SendProxyConfigHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof SendProxyConfigPacket){
            final SendProxyConfigPacket sendProxyConfigPacket = (SendProxyConfigPacket) packet;
            LiptonBungeeBridge.getInstance().updateConfig(sendProxyConfigPacket.getProxyConfig());

            List<ServerMeta> newAddedServer = new ArrayList<>();
            List<String> newAddedServerName = new ArrayList<>();
            for (ServerMeta serverMeta : sendProxyConfigPacket.getProxyConfig().getGlobalServers()){
                if (!LiptonBungeeBridge.getInstance().getProxy().getServers().containsKey(serverMeta.getServerName())){
                    newAddedServer.add(serverMeta);
                    newAddedServerName.add(serverMeta.getServerName());
                }
            }
            List<String> oldRemovedServer = new ArrayList<>();

            List<String> serverlist = new ArrayList<>();
            LiptonBungeeBridge.getInstance().getProxy().getServers().forEach((name, serverInfo) -> {
                serverlist.add(name);
            });

            for (String name : serverlist){
                if (serverMetaByName(sendProxyConfigPacket.getProxyConfig(), name) == null){
                    if (!(name.equalsIgnoreCase("lobby") || name.equalsIgnoreCase("Lobby-1")))
                        oldRemovedServer.add(name);
                }
            }

            newAddedServer.forEach(serverMeta -> {

                ServerInfo info = ProxyServer.getInstance().constructServerInfo(serverMeta.getServerName(),
                        new InetSocketAddress("127.0.0.1", serverMeta.getPort()),
                        "Lipton-Service",
                        false);
                System.out.println(serverMeta.toString());
                ProxyServer.getInstance().getServers().put(serverMeta.getServerName(), info);

                String startMessage = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyConfig().getServer_start_message().replace("{SERVER}", serverMeta.getServerName()).replace("{WRAPPER}", serverMeta.getWrapperID()).replace("{GROUP}", serverMeta.getServerGroupMeta().getGroupName());
                for (ProxiedPlayer current : ProxyServer.getInstance().getPlayers()){
                    if (current.hasPermission("liptoncloud.cloudCommand")){
                        current.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + startMessage);
                    }
                }
            });

            oldRemovedServer.forEach(name -> {

                ProxyServer.getInstance().getServers().remove(name);


                String stopMessage = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyConfig().getServer_stop_message().replace("{SERVER}", name);

                for (ProxiedPlayer current : ProxyServer.getInstance().getPlayers()){
                    if (LiptonBungeeBridge.getInstance().getProxyConfig().getCloudAdminPlayers().contains(current.getName())){
                        current.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + stopMessage);
                    }
                }
            });

            if (sendProxyConfigPacket.getProxyConfig().getMaintenance()){
                for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()){
                    if (!sendProxyConfigPacket.getProxyConfig().getMaintenancePlayer().contains(player.getName())){
                        player.disconnect(sendProxyConfigPacket.getProxyConfig().getMaintenanceKickMessage());
                    }
                }
            }



        }
    }

    private ServerMeta serverMetaByName(ProxyConfig proxyConfig, String name){
        for (ServerMeta serverMeta : proxyConfig.getGlobalServers()){
            if (serverMeta.getServerName().equalsIgnoreCase(name))
                return serverMeta;
        }
        return null;
    }
}
