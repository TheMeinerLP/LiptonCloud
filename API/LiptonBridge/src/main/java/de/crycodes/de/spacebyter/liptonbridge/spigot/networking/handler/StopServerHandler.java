package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
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
                System.exit(0);
                return;
            }
            String serverName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerName();
            if (serverName.equalsIgnoreCase(stopServerPacket.getServerName())){
                System.exit(0);
            } else {
                return;
            }

        }
    }
}
