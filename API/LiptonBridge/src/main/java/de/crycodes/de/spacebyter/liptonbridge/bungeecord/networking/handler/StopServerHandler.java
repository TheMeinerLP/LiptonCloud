package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopServerPacketProxy;
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

    public StopServerHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacketProxy){
            final StopServerPacketProxy stopServerPacketProxy = (StopServerPacketProxy) packet;
            final ServerMeta serverMeta = stopServerPacketProxy.getServerMeta();
            if (!ProxyServer.getInstance().getServers().containsKey(serverMeta.getServerName())) return;
            ProxyServer.getInstance().getServers().remove(serverMeta.getServerName());
            //TODO: BROADCAST: SERVER WAS REMOVED!
        }
        super.handel(packet);
    }
}
