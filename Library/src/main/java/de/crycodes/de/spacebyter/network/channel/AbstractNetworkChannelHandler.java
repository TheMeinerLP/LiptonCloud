package de.crycodes.de.spacebyter.network.channel;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Coded By CryCodes
 * Class: AbstractNetworkChannelHandler
 * Date : 29.05.2020
 * Time : 15:20
 * Project: CrazyCloud - V2
 */

public abstract class AbstractNetworkChannelHandler {

    private Map<Provider, NetworkChannel> networkChannelHashMap = new HashMap<>();

    public void registerChannel(NetworkChannel networkChannel){
        this.networkChannelHashMap.put(networkChannel.getProvider(),networkChannel);
    }
    public void unregisterChannel(NetworkChannel networkChannel){
        this.networkChannelHashMap.remove(networkChannel.getProvider(),networkChannel);
    }
    public void bindSocketToChannel(Socket socket,Provider provider){

    }
    public NetworkChannel getCloudChannel(){
        return new NetworkChannel(new Identifier("THUNDER"), new Provider("DEFAULT"));
    }
}
