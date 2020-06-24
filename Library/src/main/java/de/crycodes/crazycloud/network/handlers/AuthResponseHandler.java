package de.crycodes.crazycloud.network.handlers;

import de.crycodes.crazycloud.network.adapter.PacketHandlerAdapter;
import de.crycodes.crazycloud.network.channel.NetworkChannel;
import de.crycodes.crazycloud.network.logger.MessageLogger;
import de.crycodes.crazycloud.network.packet.Packet;
import de.crycodes.crazycloud.network.packets.AuthResponsePacket;

/**
 * Coded By CryCodes
 * Class: AuthResponseHandler
 * Date : 31.05.2020
 * Time : 13:19
 * Project: Networking-Framework
 */

public class AuthResponseHandler extends PacketHandlerAdapter {

    public AuthResponseHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof AuthResponsePacket){
            final AuthResponsePacket authResponsePacket = (AuthResponsePacket) packet;
            if (authResponsePacket.getResult()){
                MessageLogger.getGlobalLogger().info("CLIENT IS AUTHENTICATED!");
            } else {
                MessageLogger.getGlobalLogger().info("KEY IST NOT THE SAME AS THE KEY ON THE SERVER!");
                System.exit(-1);
            }


        }
        super.handel(packet);
    }
}
