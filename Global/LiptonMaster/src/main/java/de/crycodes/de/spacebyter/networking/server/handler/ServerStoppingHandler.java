package de.crycodes.de.spacebyter.networking.server.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ServerStoppingHandler
 * Date : 26.06.2020
 * Time : 16:47
 * Project: LiptonCloud
 */

public class ServerStoppingHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ServerStoppingHandler">
    public ServerStoppingHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ServerStoppingPacket){
            final ServerStoppingPacket serverStoppingPacket = (ServerStoppingPacket) packet;

            System.out.println(serverStoppingPacket.toString());

            liptonMaster.getServerManager().unregisterServer(serverStoppingPacket.getServerMeta().getServerName());
            liptonMaster.getMasterProxyServer().getServer().sendPacket(liptonMaster.getMasterProxyServer().getNetworkChannel(), serverStoppingPacket);
        }
    }
    //</editor-fold>
}
