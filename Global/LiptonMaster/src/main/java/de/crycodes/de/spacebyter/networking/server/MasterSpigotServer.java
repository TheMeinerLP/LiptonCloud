package de.crycodes.de.spacebyter.networking.server;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.GlobalPacketHandler;
import de.crycodes.de.spacebyter.networking.handler.RegisterHandler;
import de.crycodes.de.spacebyter.networking.handler.MessageHandler;
import de.crycodes.de.spacebyter.networking.server.handler.ServerStoppingHandler;
import de.crycodes.de.spacebyter.networking.server.handler.ServerUpdateHandler;

/**
 * Coded By CryCodes
 * Class: MasterWrapperServer
 * Date : 26.06.2020
 * Time : 12:21
 * Project: LiptonCloud
 */

public class MasterSpigotServer {

    private final Integer port;
    
    private final LiptonMaster liptonMaster;

    private ThunderServer server;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private PacketHandler packetHandler;

    //<editor-fold desc="MasterSpigotServer">
    public MasterSpigotServer(Integer port, LiptonMaster liptonMaster) {
        this.port = port;
        this.liptonMaster = liptonMaster;
        networkChannel = liptonMaster.getLiptonLibrary().getCloudChannel();
        packetHandler = liptonMaster.getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(new ServerStoppingHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new ServerUpdateHandler());
        adapterHandler.registerAdapter(new RegisterHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new MessageHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new GlobalPacketHandler(liptonMaster));

    }
    //</editor-fold>
    //<editor-fold desc="start">
    public MasterSpigotServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);

        liptonMaster.getCloudConsole().getLogger().info("Starting Master-Spigot Server on Port: (§c" + port + "§r) !");
        return this;
    }
    //</editor-fold>
    //<editor-fold desc="sendPacket">
    public MasterSpigotServer sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, server, packet);
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="getter - setter">
    public NetworkChannel getNetworkChannel() {
        return networkChannel;
    }

    public ThunderServer getServer() {
        return server;
    }
    //</editor-fold>
}
