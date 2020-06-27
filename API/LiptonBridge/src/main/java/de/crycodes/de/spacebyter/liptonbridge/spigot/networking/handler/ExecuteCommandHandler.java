package de.crycodes.de.spacebyter.liptonbridge.spigot.networking.handler;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.ExecuteCommandPacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Coded By CryCodes
 * Class: ExecuteCommandHandler
 * Date : 26.06.2020
 * Time : 16:07
 * Project: LiptonCloud
 */

public class ExecuteCommandHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof ExecuteCommandPacket){

                final ExecuteCommandPacket executeCommandPacket = (ExecuteCommandPacket) packet;
                if (LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta() == null) {
                    if (executeCommandPacket.getServerName().equalsIgnoreCase("NONE")){
                        Bukkit.getScheduler().scheduleSyncDelayedTask(LiptonSpigotBridge.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), executeCommandPacket.getCommadLine());
                            }
                        },5);
                    }
                    return;
                }
                String serverName = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta().getServerName();
                if (serverName.equalsIgnoreCase(executeCommandPacket.getServerName())){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(LiptonSpigotBridge.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), executeCommandPacket.getCommadLine());
                        }
                    },5);
                } else {
                    return;
                }


        }
    }
}
