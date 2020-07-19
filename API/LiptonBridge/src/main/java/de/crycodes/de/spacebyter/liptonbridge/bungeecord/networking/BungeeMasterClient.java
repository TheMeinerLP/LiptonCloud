package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler.*;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.ShutDownPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ProxyStoppingPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.ExecuteCommandPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.SendServerConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CreateTemplatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.DebugPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.WarningPacket;
import de.crycodes.de.spacebyter.network.ThunderClient;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
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
        networkChannel = LiptonBungeeBridge.getInstance().getCloudAPI().CloudChannel;
        adapterHandler = new AdapterHandler();



        packetHandler.registerPacket((byte) 0, RegisterPacket.class);
        packetHandler.registerPacket((byte) 1, ShutDownPacket.class);
        packetHandler.registerPacket((byte) 2, SendProxyConfigPacket.class);
        packetHandler.registerPacket((byte) 3, StopProxyPacket.class);
        packetHandler.registerPacket((byte) 4, ProxyStoppingPacket.class);
        packetHandler.registerPacket((byte) 6, ExecuteCommandPacket.class);
        packetHandler.registerPacket((byte) 7, SendServerConfigPacket.class);
        packetHandler.registerPacket((byte) 8, StopServerGroupPacket.class);
        packetHandler.registerPacket((byte) 9, StopServerPacket.class);
        packetHandler.registerPacket((byte) 10, ServerStoppingPacket.class);
        packetHandler.registerPacket((byte) 11, CopyServerPacket.class);
        packetHandler.registerPacket((byte) 12, CreateTemplatePacket.class);
        packetHandler.registerPacket((byte) 13, StartServerPacket.class);
        packetHandler.registerPacket((byte) 14, DebugPacket.class);
        packetHandler.registerPacket((byte) 15, ErrorPacket.class);
        packetHandler.registerPacket((byte) 16, InfoPacket.class);
        packetHandler.registerPacket((byte) 17, WarningPacket.class);
        packetHandler.registerPacket((byte) 18, UpdateMaintenancePacket.class);
        packetHandler.registerPacket((byte) 19, ServerGroupUpdatePacket.class);
        packetHandler.registerPacket((byte) 20, ServerUpdatePacket.class);
        packetHandler.registerPacket((byte) 21, RegisterResponsePacket.class);

        adapterHandler.registerAdapter(new AuthResponseHandler());
        adapterHandler.registerAdapter(new StopProxyHandler());
        adapterHandler.registerAdapter(new SendProxyConfigHandler());

        adapterHandler.registerAdapter(new StartServerHandler());
        adapterHandler.registerAdapter(new StopServerHandler());
    }

    public BungeeMasterClient start(){
        thunderClient = new ThunderClient(adapterHandler, networkChannel ,host, port, 5000);

        ProxyMeta proxyMeta;
        if (LiptonBungeeBridge.getInstance().getCloudAPI().getProxyMeta() == null)
            proxyMeta = new ProxyMeta("MainProxy", 1, true);
        else
            proxyMeta = LiptonBungeeBridge.getInstance().getCloudAPI().getProxyMeta();


        thunderClient.sendPacket(this.networkChannel, new RegisterPacket(proxyMeta, RegisterType.PROXY, "null"));

        System.out.println("Started ProxyMaster Client on: (" + host + ":" + port + ")");

        return this;
    }

    public NetworkChannel getNetworkChannel() {
        return networkChannel;
    }

    public ThunderClient getThunderClient() {
        return thunderClient;
    }

}
