package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: UnregisterHandler
 * Date : 26.06.2020
 * Time : 17:41
 * Project: LiptonCloud
 */

public class UnregisterHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="UnregisterHandler">
    public UnregisterHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof UnRegisterPacket){
            final UnRegisterPacket unRegisterPacket = (UnRegisterPacket) packet;
            switch (unRegisterPacket.getRegisterType()){
                case WRAPPER:
                    liptonMaster.getWrapperManager().unregisterWrapper((WrapperMeta) unRegisterPacket.getMeta());
                    liptonMaster.getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((WrapperMeta) unRegisterPacket.getMeta()).getWrapperConfig().getWrapperId());
                    break;
                case PROXY:
                    liptonMaster.getProxyManager().unregisterProxy((ProxyMeta) unRegisterPacket.getMeta());
                    liptonMaster.getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((ProxyMeta) unRegisterPacket.getMeta()).getName());
                    break;
                case SERVER:
                    final ServerMeta serverMeta = (ServerMeta) unRegisterPacket.getMeta();

                    liptonMaster.getServerManager().unregisterServer(serverMeta.getServerName());

                    liptonMaster.getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((ServerMeta) unRegisterPacket.getMeta()).getServerName());
                    break;

                default:
                    return;
            }
        }
    }
    //</editor-fold>
}
