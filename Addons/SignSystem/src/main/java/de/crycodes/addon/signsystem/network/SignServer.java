package de.crycodes.addon.signsystem.network;

import de.crycodes.addon.signsystem.network.handler.*;
import de.crycodes.addon.signsystem.packets.*;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.network.ThunderServer;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;

/**
 * Coded By CryCodes
 * Class: SignServer
 * Date : 25.06.2020
 * Time : 16:18
 * Project: LiptonCloud
 */

public class SignServer {

    private final String host;
    private final Integer port;
    private final LiptonMaster liptonMaster;

    private ThunderServer thunderServer;
    private PacketHandler packetHandler;
    private AdapterHandler adapterHandler;
    private NetworkChannel networkChannel = LiptonMaster.getInstance().getLiptonLibrary().getCloudChannel();

    public SignServer(LiptonMaster liptonMaster, String host, Integer port) {
        this.liptonMaster = liptonMaster;
        this.host = host;
        this.port = port;
    }

    public void start() {
        adapterHandler = new AdapterHandler();
        packetHandler = new PacketHandler();

        packetHandler.registerPacket((byte) 1, LoadSignPacket.class);
        packetHandler.registerPacket((byte) 2, RegisterSignPacket.class);
        packetHandler.registerPacket((byte) 3, SendServerListPacket.class);
        packetHandler.registerPacket((byte) 4, UnRegisterSignPacket.class);
        packetHandler.registerPacket((byte) 5, UpdateServerListPacket.class);

        adapterHandler.registerAdapter( new RegisterSignPacketHandler());
        adapterHandler.registerAdapter( new UnRegisterSignPacketHandler());

        thunderServer = new ThunderServer(adapterHandler, networkChannel, port);
        liptonMaster.getColouredConsoleProvider().info("[SIGNSYSTEM] Starting NetworkServer on (" + host + ":" + port + ")!");
    }

    public void sendPacket(Packet packet){
        this.packetHandler.sendPacket(this.networkChannel, this.thunderServer, packet);
    }
}
