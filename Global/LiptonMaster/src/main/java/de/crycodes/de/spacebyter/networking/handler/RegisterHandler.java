package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthHandler
 * Date : 26.06.2020
 * Time : 11:54
 * Project: LiptonCloud
 */

public class RegisterHandler extends PacketHandlerAdapter {
    
    private final LiptonMaster liptonMaster;

    //<editor-fold desc="RegisterHandler">
    public RegisterHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof RegisterPacket){
            final RegisterPacket registerPacket = (RegisterPacket) packet;
            switch (registerPacket.getRegisterType()){
                case WRAPPER:
                    String key = registerPacket.getKey();
                    if (key.equalsIgnoreCase("null")) {
                        liptonMaster.getMasterWrapperServer().sendPacket(new RegisterResponsePacket(false, "Wrong Wrapper Key or missing Key!"));
                        liptonMaster.getColouredConsoleProvider().warning("Connection with wrong or missing key found!");
                    }

                    liptonMaster.getAuthManager().checkKeys(liptonMaster.getAuthManager().getKey(), key, result -> {
                        if (!result) {
                            liptonMaster.getMasterWrapperServer().sendPacket(new RegisterResponsePacket(result, "Wrong Wrapper Key or missing Key!"));
                            liptonMaster.getColouredConsoleProvider().warning("Connection with wrong or missing key found!");
                        }

                        if (result){
                            liptonMaster.getWrapperManager().registerWrapper((WrapperMeta) registerPacket.getMeta(),aBoolean -> {
                                liptonMaster.getMasterWrapperServer().sendPacket(new RegisterResponsePacket(aBoolean, "no WrapperGroup found!"));
                            });
                        }
                    });

                    break;
                case PROXY:
                    liptonMaster.getProxyManager().registerProxy((ProxyMeta) registerPacket.getMeta(), result -> {
                        liptonMaster.getMasterProxyServer().getServer().sendPacket(
                                liptonMaster.getMasterProxyServer().getNetworkChannel(),
                                new RegisterResponsePacket(result, "-"));
                    });
                    break;
                case SERVER:
                    if (registerPacket.getMeta() instanceof ServerMeta){
                        final ServerMeta serverMeta = (ServerMeta) registerPacket.getMeta();
                        liptonMaster.getServerManager().registerServer(serverMeta.getServerName());
                    }
                    break;

                default:
                    return;
            }
        }
    }
    //</editor-fold>
}
