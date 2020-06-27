package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthHandler
 * Date : 26.06.2020
 * Time : 11:54
 * Project: LiptonCloud
 */

public class RegisterHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof RegisterPacket){
            final RegisterPacket registerPacket = (RegisterPacket) packet;
            switch (registerPacket.getRegisterType()){
                case WRAPPER:
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Trying to Register Wrapper: " + ((WrapperMeta) registerPacket.getMeta()).getWrapperConfig().getWrapperId());
                    LiptonMaster.getInstance().getWrapperManager().registerWrapper((WrapperMeta) registerPacket.getMeta(),result -> {
                        LiptonMaster.getInstance().getMasterWrapperServer().sendPacket(new RegisterResponsePacket(result));
                    });
                    break;
                case PROXY:
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Trying to Register Proxy: " + ((ProxyMeta) registerPacket.getMeta()).getName());
                    LiptonMaster.getInstance().getProxyManager().registerProxy((ProxyMeta) registerPacket.getMeta(), result -> {
                        LiptonMaster.getInstance().getMasterProxyServer().getServer().sendPacket(
                                LiptonMaster.getInstance().getMasterProxyServer().getNetworkChannel(),
                                new RegisterResponsePacket(result));
                    });
                    break;
                case SERVER:
                    LiptonMaster.getInstance().getColouredConsoleProvider().info("Trying to Register Server: " + ((ServerMeta) registerPacket.getMeta()).getServerName());
                    LiptonMaster.getInstance().getServerGlobalManager().registerServer((ServerMeta) registerPacket.getMeta(),result -> {
                        LiptonMaster.getInstance().getMasterSpigotServer().sendPacket(new RegisterResponsePacket(result));
                    });
                    break;

                default:
                    return;
            }
        }
    }
}
