package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CreateTemplatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.handler.*;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;

/**
 * Coded By CryCodes
 * Class: WrapperMasterClient
 * Date : 26.06.2020
 * Time : 13:25
 * Project: LiptonCloud
 */

public class WrapperMasterClient {

    private PacketHandler packetHandler;
    private NetworkChannel networkChannel;
    private AdapterHandler adapterHandler;
    private ThunderClient thunderClient;

    private final String host;
    private final Integer port;

    public WrapperMasterClient(String host, Integer port) {
        this.port = port;
        this.host = host;
        packetHandler = LiptonWrapper.getInstance().getPacketHandler();
        networkChannel = LiptonWrapper.getInstance().getLiptonLibrary().getCloudChannel();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter( new AuthResponseHandler());
        adapterHandler.registerAdapter( new CopyServerHandler());
        adapterHandler.registerAdapter( new StartServerHandler());
        adapterHandler.registerAdapter(new CreateTemplateHandler());
        adapterHandler.registerAdapter(new StopServerHandler());

    }

    public WrapperMasterClient start(){
        thunderClient = new ThunderClient(adapterHandler, networkChannel ,host, port, 5000);

        sendPacket(new RegisterPacket(new WrapperMeta(true,
                new WrapperConfig(
                        LiptonWrapper.getInstance().getWrapperConfig().getWrapperID(),
                        LiptonWrapper.getInstance().getWrapperConfig().getHost(),
                        LiptonWrapper.getInstance().getWrapperConfig().isAutoUpdate())), RegisterType.WRAPPER));

        LiptonWrapper.getInstance().getColouredConsoleProvider().info("Startet WrapperMaster Client on: (" + host + ":" + port + ")");

        return this;
    }

    public void sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, thunderClient, packet);
    }
}
