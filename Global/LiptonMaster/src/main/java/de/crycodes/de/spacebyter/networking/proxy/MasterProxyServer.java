package de.crycodes.de.spacebyter.networking.proxy;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ProxyStoppingPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ReloadPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.handler.AuthHandler;
import de.crycodes.de.spacebyter.networking.proxy.handler.ProxyStoppingHandler;
import de.crycodes.de.spacebyter.networking.proxy.handler.ReloadHandler;
import de.crycodes.de.spacebyter.networking.proxy.handler.ServerGroupUpdateHandler;

/**
 * Coded By CryCodes
 * Class: MasterWrapperServer
 * Date : 26.06.2020
 * Time : 12:21
 * Project: LiptonCloud
 */

public class MasterProxyServer {

    private final Integer port;

    private ThunderServer server;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private PacketHandler packetHandler;

    public MasterProxyServer(Integer port) {
        this.port = port;
        networkChannel = LiptonMaster.getInstance().getLiptonLibrary().getMaster_Wrapper_Channel();
        packetHandler = LiptonMaster.getInstance().getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(ProxyStoppingPacket.class, new ProxyStoppingHandler(networkChannel));
        adapterHandler.registerAdapter(ReloadPacket.class, new ReloadHandler(networkChannel));
        adapterHandler.registerAdapter(ServerGroupUpdatePacket.class, new ServerGroupUpdateHandler(networkChannel));
        adapterHandler.registerAdapter(RegisterPacket.class, new AuthHandler(networkChannel));

    }
    public MasterProxyServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);

        LiptonMaster.getInstance().getColouredConsoleProvider().info("Starting Proxy-Wrapper Server on Port: (§c" + port + "§r) !");
        return this;
    }
    public MasterProxyServer sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, server, packet);
        return this;
    }
}
