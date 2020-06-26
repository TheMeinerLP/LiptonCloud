package de.crycodes.de.spacebyter.liptonbridge.spigot.networking;

import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler.*;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.ExecuteCommandPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.SendServerConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.network.ThunderClient;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.network.packets.AuthResponsePacket;

/**
 * Coded By CryCodes
 * Class: BungeeMasterClient
 * Date : 26.06.2020
 * Time : 15:13
 * Project: LiptonCloud
 */

public class SpigotMasterClient {


    private PacketHandler packetHandler;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private ThunderClient thunderClient;

    private final String host;
    private final Integer port;

    public SpigotMasterClient(String host, Integer port) {
        this.port = port;
        this.host = host;
        packetHandler = new PacketHandler();
        networkChannel = LiptonLibrary.getInstance().getProxy_Master_Channel();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(ExecuteCommandPacket.class, new ExecuteCommandHandler(networkChannel));
        adapterHandler.registerAdapter(SendServerConfigPacket.class, new SendServerConfigHandler(networkChannel));
        adapterHandler.registerAdapter(StopServerPacket.class, new StopServerHandler(networkChannel));
        adapterHandler.registerAdapter(StopServerGroupPacket.class, new StopServerGroupHandler(networkChannel));
        adapterHandler.registerAdapter(AuthResponsePacket.class, new AuthResponseHandler(networkChannel));

    }

    public SpigotMasterClient start(){
        thunderClient = new ThunderClient(adapterHandler, networkChannel ,host, port, 5000);

        //REMOVE
            sendPacket(new RegisterPacket(new ServerMeta("Lobby-1",
                    1,
                    new ServerGroupMeta("Lobby", 512, 128, true, true, 10, 2),
                    "Wrapper-1",
                    "127.0.0.1",
                    30000),
                    RegisterType.SERVER))
            ; //TODO: GET SERVERMETA
        //REMOVE

        System.out.println("Started ProxyMaster Client on: (" + host + ":" + port + ")");

        return this;
    }

    public void sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, thunderClient, packet);
    }

}
