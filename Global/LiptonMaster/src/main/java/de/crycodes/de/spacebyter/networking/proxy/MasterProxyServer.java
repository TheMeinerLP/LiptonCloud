package de.crycodes.de.spacebyter.networking.proxy;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.GlobalPacketHandler;
import de.crycodes.de.spacebyter.networking.handler.RegisterHandler;
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
    
    private final LiptonMaster liptonMaster;

    //<editor-fold desc="MasterProxyServer">
    public MasterProxyServer(Integer port, LiptonMaster liptonMaster) {
        this.port = port;
        this.liptonMaster = liptonMaster;
        networkChannel = liptonMaster.getLiptonLibrary().getCloudChannel();
        packetHandler = liptonMaster.getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(new ProxyStoppingHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new ReloadHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new ServerGroupUpdateHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new RegisterHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new GlobalPacketHandler(liptonMaster));
    }
    //</editor-fold>
    //<editor-fold desc="start">
    public MasterProxyServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);

        liptonMaster.getColouredConsoleProvider().info("Starting Proxy-Wrapper Server on Port: (§c" + port + "§r) !");
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="getter - setter">
    public ThunderServer getServer() {
        return server;
    }

    public NetworkChannel getNetworkChannel() {
        return networkChannel;
    }
    //</editor-fold>
}
