package de.crycodes.de.spacebyter.networking;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.handler.RegisterHandler;
import de.crycodes.de.spacebyter.networking.handler.MaintenanceUpdateHandler;
import de.crycodes.de.spacebyter.networking.handler.MessageHandler;
import de.crycodes.de.spacebyter.networking.handler.UnregisterHandler;

/**
 * Coded By CryCodes
 * Class: MasterWrapperServer
 * Date : 26.06.2020
 * Time : 12:21
 * Project: LiptonCloud
 */

public class MasterWrapperServer {

    private final Integer port;
    private final LiptonMaster liptonMaster;

    private ThunderServer server;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private PacketHandler packetHandler;

    //<editor-fold desc="MasterWrapperServer">
    public MasterWrapperServer(Integer port, LiptonMaster liptonMaster) {
        this.port = port;
        this.liptonMaster = liptonMaster;
        networkChannel = this.liptonMaster.getLiptonLibrary().getCloudChannel();
        packetHandler = this.liptonMaster.getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(new RegisterHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new UnregisterHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new MaintenanceUpdateHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new MessageHandler(this.liptonMaster));
        adapterHandler.registerAdapter(new GlobalPacketHandler(this.liptonMaster));
    }
    //</editor-fold>
    //<editor-fold desc="start">
    public MasterWrapperServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);


        liptonMaster.getCloudConsole().getLogger().info("Starting Master-Wrapper Server on Port: (§c" + port + "§r) !");
        return this;
    }
    //</editor-fold>
    //<editor-fold desc="sendPacket">
    public MasterWrapperServer sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, server, packet);
        return this;
    }
    //</editor-fold>
}
