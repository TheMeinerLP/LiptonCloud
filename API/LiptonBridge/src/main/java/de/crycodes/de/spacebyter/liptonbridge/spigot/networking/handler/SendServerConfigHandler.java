package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.SendServerConfigPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: SendServerConfigHandler
 * Date : 26.06.2020
 * Time : 16:07
 * Project: LiptonCloud
 */

public class SendServerConfigHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof SendServerConfigPacket){
            final SendServerConfigPacket serverConfigPacket = (SendServerConfigPacket) packet;
            LiptonSpigotBridge.getInstance().setServerConfig(serverConfigPacket.getServerConfig());
        }

    }
}
