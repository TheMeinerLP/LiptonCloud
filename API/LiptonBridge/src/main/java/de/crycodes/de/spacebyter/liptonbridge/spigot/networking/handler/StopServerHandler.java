package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerHandler
 * Date : 26.06.2020
 * Time : 16:08
 * Project: LiptonCloud
 */

public class StopServerHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacket){
            final StopServerPacket stopServerPacket = (StopServerPacket) packet;
            if (LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta() == null) {

                ServerMeta serverMeta;
                if (LiptonSpigotBridge.getInstance().getCloudAPI().getProxyMeta() == null)
                    serverMeta = new ServerMeta("NONE", 1, new ServerGroupMeta("NONE", 512, 128, false,false, 0,0), "NONE", "127.0.0.1", 0);
                else
                    serverMeta = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta();

                LiptonSpigotBridge.getInstance().getSpigotMasterClient().getThunderClient().sendPacket(LiptonSpigotBridge.getInstance().getSpigotMasterClient().getNetworkChannel(), new ServerStoppingPacket(serverMeta));
                System.exit(0);
                return;
            }

            ServerMeta serverMeta;
            if (LiptonSpigotBridge.getInstance().getCloudAPI().getProxyMeta() == null)
                serverMeta = new ServerMeta("NONE", 1, new ServerGroupMeta("NONE", 512, 128, false,false, 0,0), "NONE", "127.0.0.1", 0);
            else
                serverMeta = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta();

            String serverName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerName();
            if (serverName.equalsIgnoreCase(stopServerPacket.getServerName())){
                LiptonSpigotBridge.getInstance().getSpigotMasterClient().getThunderClient().sendPacket(LiptonSpigotBridge.getInstance().getSpigotMasterClient().getNetworkChannel(), new ServerStoppingPacket(serverMeta));
                System.exit(0);
            } else {
                return;
            }

        }
    }
}
