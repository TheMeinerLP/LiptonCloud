package de.crycodes.crazycloud.network.handlers;

import de.crycodes.crazycloud.network.adapter.PacketHandlerAdapter;
import de.crycodes.crazycloud.network.bootsrap.ClientInterface;
import de.crycodes.crazycloud.network.bootsrap.ServerInterface;
import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.keyauth.KeyManager;
import de.crycodes.crazycloud.network.packet.Packet;
import de.crycodes.crazycloud.network.packet.PacketHandler;
import de.crycodes.crazycloud.network.packets.AuthPacket;
import de.crycodes.crazycloud.network.packets.AuthResponsePacket;

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
        super(networkChannel);
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
        super.handel(packet);
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
