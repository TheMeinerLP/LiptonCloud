package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import org.bukkit.Bukkit;

/**
 * Coded By CryCodes
 * Class: StopServerHandler
 * Date : 26.06.2020
 * Time : 16:08
 * Project: LiptonCloud
 */

public class StopServerHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof StopServerPacket){
            final StopServerPacket stopServerPacket = (StopServerPacket) packet;
            if (LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta() == null) {
             //   System.exit(0);
                Bukkit.shutdown();
                return;
            }

            String serverName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerName();
            if (serverName.equalsIgnoreCase(stopServerPacket.getServerName())){
              //  System.exit(0);
                Bukkit.shutdown();
            } else {
                return;
            }

        }
    }
}
