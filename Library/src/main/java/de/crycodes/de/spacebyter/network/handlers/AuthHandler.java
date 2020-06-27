package de.crycodes.de.spacebyter.network.handlers;

import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.bootsrap.ServerInterface;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.keyauth.KeyManager;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.network.packets.AuthPacket;
import de.crycodes.de.spacebyter.network.packets.AuthResponsePacket;

/**
 * Coded By CryCodes
 * Class: AuthHandler
 * Date : 31.05.2020
 * Time : 13:06
 * Project: Networking-Framework
 */

public class AuthHandler extends PacketHandlerAdapter {

    private final KeyManager keyManager;
    private final PacketHandler packetHandler;
    private final NetworkChannel networkChannel;
    private ServerInterface serverInterface;

    public AuthHandler(NetworkChannel networkChannel, PacketHandler packetHandler, ServerInterface serverInterface, KeyManager keyManager) {
        this.keyManager = keyManager;
        this.networkChannel = networkChannel;
        this.packetHandler = packetHandler;
        this.serverInterface = serverInterface;
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof AuthPacket){
            final AuthPacket authPacket = (AuthPacket) packet;
            final String key = authPacket.getKey();
            if (getKeyManager().isKey(key)){
                packetHandler.sendPacket(networkChannel, serverInterface, new AuthResponsePacket(true));
            } else {
                packetHandler.sendPacket(networkChannel, serverInterface, new AuthResponsePacket(false));
            }
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }


    public ServerInterface getServerInterface() {
        return serverInterface;
    }

    public void setServerInterface(ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
    }
}
