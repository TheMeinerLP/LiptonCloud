package de.crycodes.crazycloud.network.channel;

import java.net.Socket;

/**
 * Coded By CryCodes
 * Class: NetworkChannelHandler
 * Date : 29.05.2020
 * Time : 14:44
 * Project: CrazyCloud - V2
 */

public class NetworkChannelHandler extends AbstractNetworkChannelHandler {

    @Override
    public void registerChannel(NetworkChannel networkChannel) {
        super.registerChannel(networkChannel);
    }

    @Override
    public NetworkChannel getCloudChannel() {
        return super.getCloudChannel();
    }

    @Override
    public void unregisterChannel(NetworkChannel networkChannel) {
        super.unregisterChannel(networkChannel);
    }

    @Override
    public void bindSocketToChannel(Socket socket, Provider provider) {
        super.bindSocketToChannel(socket, provider);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
