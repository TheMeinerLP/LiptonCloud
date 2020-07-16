package de.crycodes.de.spacebyter.networking.proxy.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ReloadPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: ReloadHandler
 * Date : 26.06.2020
 * Time : 16:40
 * Project: LiptonCloud
 */

public class ReloadHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ReloadHandler">
    public ReloadHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="Description">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof ReloadPacket){
            final ReloadPacket reloadPacket = (ReloadPacket) packet;
            liptonMaster.getServerGroupConfig().getServerMetas();
            liptonMaster.getMasterConfig().reload();
            liptonMaster.getServerManager().start();
            liptonMaster.getColouredConsoleProvider().info("Cloud was Reloaded!");
        }
    }
    //</editor-fold>
}
