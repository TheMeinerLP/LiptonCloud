package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: UnregisterHandler
 * Date : 26.06.2020
 * Time : 17:41
 * Project: LiptonCloud
 */

public class UnregisterHandler extends PacketHandlerAdapter {

    public UnregisterHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof UnRegisterPacket){
            final UnRegisterPacket unRegisterPacket = (UnRegisterPacket) packet;
            switch (unRegisterPacket.getRegisterType()){
                case WRAPPER:
                    LiptonMaster.getInstance().getWrapperManager().unregisterWrapper((WrapperMeta) unRegisterPacket.getMeta());
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((WrapperMeta) unRegisterPacket.getMeta()).getWrapperConfig().getWrapperId());
                    break;
                case PROXY:
                    LiptonMaster.getInstance().getProxyManager().unregisterProxy((ProxyMeta) unRegisterPacket.getMeta());
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((ProxyMeta) unRegisterPacket.getMeta()).getName());
                    break;
                case SERVER:
                    LiptonMaster.getInstance().getServerGlobalManager().unregisterServer((ServerMeta) unRegisterPacket.getMeta());
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Unregistered " + unRegisterPacket.getRegisterType().name() + " | " + ((ServerMeta) unRegisterPacket.getMeta()).getServerName());
                    break;

                default:
                    return;
            }
        }
    }
}
