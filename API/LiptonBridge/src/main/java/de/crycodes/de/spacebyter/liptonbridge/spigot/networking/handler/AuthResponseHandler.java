package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: AuthResponseHandler
 * Date : 26.06.2020
 * Time : 16:13
 * Project: LiptonCloud
 */

public class AuthResponseHandler extends PacketHandlerAdapter {

    public AuthResponseHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof RegisterResponsePacket){
            if (((RegisterResponsePacket) packet).isAuthenticated())
                System.out.println("Connected To Master");
            else
                System.exit(ExitUtil.CONTROLLERKEY_MISSING);
        }
        super.handel(packet);
    }
}
