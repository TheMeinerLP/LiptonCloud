package de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: SendProxyConfigHandler
 * Date : 26.06.2020
 * Time : 15:21
 * Project: LiptonCloud
 */

public class SendProxyConfigHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        System.out.println("NEW PACKET:" + packet.toString());
        if (packet instanceof SendProxyConfigPacket){
            final SendProxyConfigPacket sendProxyConfigPacket = (SendProxyConfigPacket) packet;
            LiptonBungeeBridge.getInstance().updateConfig(sendProxyConfigPacket.getProxyConfig());
            System.out.println("UPDATED PROXY CONFIG");
        }
    }
}
