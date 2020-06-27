package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: StopServerGroupHandler
 * Date : 26.06.2020
 * Time : 16:08
 * Project: LiptonCloud
 */

public class StopServerGroupHandler extends PacketHandlerAdapter {


    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerGroupPacket){
            final StopServerGroupPacket stopServerGroupPacket = (StopServerGroupPacket) packet;
            if (LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta() == null) {
                System.exit(0);
                return;
            }
            String serverGroupName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerGroupMeta().getGroupName();
            if (serverGroupName.equalsIgnoreCase(stopServerGroupPacket.getServerGroupMeta().getGroupName())){
                System.exit(stopServerGroupPacket.getStopID());
            } else {
                return;
            }

        }
    }
}
