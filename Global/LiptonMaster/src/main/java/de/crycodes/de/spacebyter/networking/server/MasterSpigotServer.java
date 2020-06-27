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

    private ThunderServer server;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private PacketHandler packetHandler;

    public MasterSpigotServer(Integer port) {
        this.port = port;
        networkChannel = LiptonMaster.getInstance().getLiptonLibrary().getCloudChannel();
        packetHandler = LiptonMaster.getInstance().getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(new ServerStoppingHandler());
        adapterHandler.registerAdapter(new ServerUpdateHandler());
        adapterHandler.registerAdapter(new RegisterHandler());
        adapterHandler.registerAdapter(new MessageHandler());
        adapterHandler.registerAdapter(new GlobalPacketHandler());

    }
    public MasterSpigotServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);

        LiptonMaster.getInstance().getColouredConsoleProvider().info("Starting Master-Spigot Server on Port: (§c" + port + "§r) !");
        return this;
    }
    public MasterSpigotServer sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, server, packet);
        return this;
    }
}
