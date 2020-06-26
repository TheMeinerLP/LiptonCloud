package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler.*;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StartServerPacketProxy;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopServerPacketProxy;
import de.crycodes.de.spacebyter.network.ThunderClient;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;

/**
 * Coded By CryCodes
 * Class: BungeeMasterClient
 * Date : 26.06.2020
 * Time : 15:13
 * Project: LiptonCloud
 */

public class BungeeMasterClient {


    private PacketHandler packetHandler;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private ThunderClient thunderClient;

    private final String host;
    private final Integer port;

    public BungeeMasterClient(String host, Integer port) {
        this.port = port;
        this.host = host;
        packetHandler = new PacketHandler();
        networkChannel = LiptonLibrary.getInstance().getProxy_Master_Channel();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter(RegisterResponsePacket.class, new AuthResponseHandler(networkChannel));
        adapterHandler.registerAdapter(StopProxyPacket.class, new StopProxyHandler(networkChannel));
        adapterHandler.registerAdapter(SendProxyConfigPacket.class, new SendProxyConfigHandler(networkChannel));

        adapterHandler.registerAdapter(StartServerPacketProxy.class, new StartServerHandler(networkChannel));
        adapterHandler.registerAdapter(StopServerPacketProxy.class, new StopServerHandler(networkChannel));

    }

    public BungeeMasterClient start(){
        thunderClient = new ThunderClient(adapterHandler, networkChannel ,host, port, 5000);

        sendPacket(new RegisterPacket(new ProxyMeta("Proxy-1", 1, false),RegisterType.PROXY)); //TODO: GET PROXYMETA

        System.out.println("Started ProxyMaster Client on: (" + host + ":" + port + ")");

        return this;
    }

    public void sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, thunderClient, packet);
    }

}
