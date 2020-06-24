package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.bootsrap.ServerInterface;
import de.crycodes.de.spacebyter.network.channel.Channel;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.net.Socket;

/**
 * Coded By CryCodes
 * Class: ThunderServer
 * Date : 29.05.2020
 * Time : 16:06
 * Project: Networking-Framework
 */

public class ThunderServer extends BackhandedServer implements ServerInterface {

    public ThunderServer(AdapterHandler adapterHandler,NetworkChannel networkChannel, int port) {
        super(port);

        registerMethod(networkChannel.getChannelID(), new PacketRunner() {

            @Override
            public void run(Channel pack, Socket socket) {
                Packet packet = (Packet) pack.get(1);
                adapterHandler.handelAdapterHandler(networkChannel,packet);

            }
        });

    }

    @Override
    public void preStart() {
        registerLoginMethod();
    }

    @Override
    public void sendPacket(NetworkChannel networkChannel, Packet packet) {
        broadcastMessage(new Channel(networkChannel.getChannelID(), packet));
    }
}
