package de.crycodes.de.spacebyter.networking.proxy.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ServerGroupUpdateHandler
 * Date : 26.06.2020
 * Time : 17:03
 * Project: LiptonCloud
 */

public class ServerGroupUpdateHandler extends PacketHandlerAdapter {

    public ServerGroupUpdateHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof ServerGroupUpdatePacket){
            final ServerGroupUpdatePacket serverGroupUpdatePacket = (ServerGroupUpdatePacket) packet;
            LiptonMaster.getInstance().getServerGroupConfig().replace(serverGroupUpdatePacket.getUpdatedServerGroupMeta());
            LiptonMaster.getInstance().getColouredConsoleProvider().info("Updated ServerGroup: " + ((ServerGroupUpdatePacket) packet).getUpdatedServerGroupMeta().getGroupName());
        }
        super.handel(packet);
    }
}
