package de.crycodes.de.spacebyter.networking.proxy.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ServerGroupUpdateHandler
 * Date : 26.06.2020
 * Time : 17:03
 * Project: LiptonCloud
 */

public class ServerGroupUpdateHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ServerGroupUpdateHandler">
    public ServerGroupUpdateHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ServerGroupUpdatePacket){
            final ServerGroupUpdatePacket serverGroupUpdatePacket = (ServerGroupUpdatePacket) packet;
            liptonMaster.getServerGroupConfig().replace(serverGroupUpdatePacket.getUpdatedServerGroupMeta());
            liptonMaster.getColouredConsoleProvider().info("Updated ServerGroup: " + ((ServerGroupUpdatePacket) packet).getUpdatedServerGroupMeta().getGroupName());
        }
    }
    //</editor-fold>
}
