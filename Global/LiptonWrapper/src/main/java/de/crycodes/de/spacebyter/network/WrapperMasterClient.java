package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.auth.AuthManager;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.handler.*;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import org.checkerframework.checker.units.qual.A;

import java.io.File;

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
    private AuthManager authManager;

    private final String host;
    private final Integer port;
    
    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="WrapperMasterClient">
    public WrapperMasterClient(String host, Integer port, LiptonWrapper liptonWrapper) {
        this.port = port;
        this.host = host;
        this.liptonWrapper = liptonWrapper;
        this.authManager = new AuthManager(new File("./liptonWrapper/KEY.json"), new File("null"),liptonWrapper.getColouredConsoleProvider());
        packetHandler = liptonWrapper.getPacketHandler();
        networkChannel = liptonWrapper.getLiptonLibrary().getCloudChannel();
        adapterHandler = new AdapterHandler();

        adapterHandler.registerAdapter( new AuthResponseHandler(liptonWrapper));
        adapterHandler.registerAdapter( new CopyServerHandler(liptonWrapper));
        adapterHandler.registerAdapter( new StartServerHandler(liptonWrapper));
        adapterHandler.registerAdapter(new CreateTemplateHandler(liptonWrapper));
        adapterHandler.registerAdapter(new StopServerHandler(liptonWrapper));

    }
    //</editor-fold>

    //<editor-fold desc="start">
    public WrapperMasterClient start(){
        thunderClient = new ThunderClient(adapterHandler, networkChannel ,host, port, 5000);

        sendPacket(new RegisterPacket(new WrapperMeta(true,
                new WrapperConfig(
                        liptonWrapper.getWrapperConfig().getWrapperID(),
                        liptonWrapper.getWrapperConfig().getHost(),
                        liptonWrapper.getWrapperConfig().isAutoUpdate())), RegisterType.WRAPPER, this.authManager.getKey()));

        liptonWrapper.getColouredConsoleProvider().getLogger().info("Startet WrapperMaster Client on: (" + host + ":" + port + ")");

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="sendPacket">
    public void sendPacket(Packet packet){
        this.packetHandler.sendPacket(networkChannel, thunderClient, packet);
    }
    //</editor-fold>
}
