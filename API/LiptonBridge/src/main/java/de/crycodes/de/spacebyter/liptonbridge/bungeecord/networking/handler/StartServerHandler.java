package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StartServerPacketProxy;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
        if (packet instanceof StartServerPacket){
            final StartServerPacket startServerPacketProxy = (StartServerPacket) packet;
            
            //TODO: BROADCAST: SERVER WAS ADDED!

            String startMessage = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyConfig().getServer_start_message();

        }
    }
}