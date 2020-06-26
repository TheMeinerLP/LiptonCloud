package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.ExecuteCommandPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import org.bukkit.Bukkit;

/**
 * Coded By CryCodes
 * Class: ExecuteCommandHandler
 * Date : 26.06.2020
 * Time : 16:07
 * Project: LiptonCloud
 */

public class ExecuteCommandHandler extends PacketHandlerAdapter {

    public ExecuteCommandHandler(NetworkChannel networkChannel) {
        super(networkChannel);
    }

    @Override
    public void handel(Packet packet) {
        if (packet instanceof ExecuteCommandPacket){
            final ExecuteCommandPacket executeCommandPacket = (ExecuteCommandPacket) packet;
            if (LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta() == null) {
                System.exit(0);
                return;
            }
            String serverName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerName();
            if (serverName.equalsIgnoreCase(executeCommandPacket.getServerName())){
                Bukkit.getConsoleSender().sendMessage(executeCommandPacket.getCommadLine());
            } else {
                return;
            }

        }
        super.handel(packet);
    }
}
