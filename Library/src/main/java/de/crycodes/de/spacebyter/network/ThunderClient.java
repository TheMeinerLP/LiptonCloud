package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.network.adapter.AdapterHandler;
import de.crycodes.de.spacebyter.network.bootsrap.ClientInterface;
import de.crycodes.de.spacebyter.network.channel.Channel;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.net.Socket;

/**
 * Coded By CryCodes
 * Class: ThunderClient
 * Date : 29.05.2020
 * Time : 16:06
 * Project: Networking-Framework
 */

public class ThunderClient extends BackhandedClient implements ClientInterface {

    public ThunderClient(AdapterHandler adapterHandler, NetworkChannel networkChannel, String hostname, int port, int timeout) {
        super(hostname, port, timeout);

        registerMethod(networkChannel.getChannelID(), new PacketRunner() {

            @Override
            public void run(Channel pack, Socket socket) {
                Packet packet = (Packet) pack.get(1);
                adapterHandler.handelAdapterHandler(networkChannel,packet);
            }
        });

        start();
    }

    @Override
    public void sendPacket(NetworkChannel networkChannel, Packet packet) {
        sendMessage(new Channel(networkChannel.getChannelID(), packet));
    }
}
