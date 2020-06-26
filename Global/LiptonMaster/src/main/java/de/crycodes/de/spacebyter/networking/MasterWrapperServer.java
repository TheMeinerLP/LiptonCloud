package de.crycodes.de.spacebyter.networking;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.handler.AuthHandler;
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

    private ThunderServer server;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private PacketHandler packetHandler;

    public MasterWrapperServer(Integer port) {
        this.port = port;
        networkChannel = LiptonMaster.getInstance().getLiptonLibrary().getMaster_Wrapper_Channel();
        packetHandler = LiptonMaster.getInstance().getPacketHandler();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(RegisterPacket.class, new AuthHandler(networkChannel));
        adapterHandler.registerAdapter(UnRegisterPacket.class, new UnregisterHandler(networkChannel));
        adapterHandler.registerAdapter(UpdateMaintenancePacket.class, new MaintenanceUpdateHandler(networkChannel));
        adapterHandler.registerAdapter(ErrorPacket.class, new MessageHandler(networkChannel));
    }
    public MasterWrapperServer start(){
        server = new ThunderServer(adapterHandler, networkChannel, port);

        LiptonMaster.getInstance().getColouredConsoleProvider().info("Starting Master-Wrapper Server on Port: (§c" + port + "§r) !");
        return this;
    }
    public MasterWrapperServer sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, server, packet);
        return this;
    }
}
