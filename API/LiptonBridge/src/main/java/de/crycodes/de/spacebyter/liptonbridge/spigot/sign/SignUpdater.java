package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import com.google.gson.internal.LinkedTreeMap;
import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.enums.SignState;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptonbridge.spigot.util.ServerPinger;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coded By CryCodes
 * Class: SignUpdater
 * Date : 20.07.2020
 * Time : 16:10
 * Project: LiptonCloud
 */

public class SignUpdater implements Runnable{

    private final LiptonSpigotBridge plugin;
    private final SignCreator signCreator;
    private final List<String> groupNames;

    private ServerPinger serverPinger = null;
    private BukkitTask bukkitTask;


    public SignUpdater(LiptonSpigotBridge plugin) {
        this.plugin = plugin;
        this.signCreator = plugin.getSignCreator();
        this.groupNames = new ArrayList<>();
        this.serverPinger = plugin.getServerPinger();
        this.bukkitTask = plugin.getServer().getScheduler().runTaskTimer(plugin, this, 20, 20);
    }

    @Override
    public void run() {

        groupNames.clear();
        List<ServerMeta> serverMetas = new ArrayList<>();

        if (plugin.getServerConfig() == null) return;

        for (ServerGroupMeta globalServerGroup : plugin.getServerConfig().getGlobalServerGroups()) {
            String groupName = globalServerGroup.getGroupName();

            serverMetas.clear();

            plugin.getServerConfig().getGlobalServers().forEach(serverMeta -> {
                if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(groupName))
                    serverMetas.add(serverMeta);
            });

            if (serverMetas.isEmpty()) return;

            serverMetas.forEach(current -> {

                int serverId = current.getId();

                if (this.signCreator.getSignConfig().getSignGroupAfterName(groupName) == null) {
                    System.out.println("NO SERVERSIGNS FOUND!");
                    return;
                }

                HashMap<Integer, CloudSign> signs = this.signCreator.getSignConfig().getSignGroupAfterName(groupName).getCloudSignHashMap();

                //DEBUG

                System.out.println(signs);

                //DEBUG

                CloudSign cloudSign = signs.get(serverId);

                if (cloudSign == null)
                    return;

                Location bukkitLocation = new Location(Bukkit.getWorld(
                        cloudSign.getWorld())
                        ,cloudSign.getX()
                        ,cloudSign.getY()
                        ,cloudSign.getZ()
                );

                try {
                    serverPinger.pingServer(current.getHost(), current.getPort(), 2500);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                createSign(bukkitLocation, current.getServerName(), serverPinger.getPlayers(), serverPinger.getMaxplayers(), serverPinger.getMotd());
            });
         }
    }

    public void createSign(Location location, String serverName, int min, int max,String motd) {

        Block blockAt = plugin.getServer().getWorld("world").getBlockAt(location);

        if (!blockAt.getType().equals(Material.WALL_SIGN)) return;

        if(blockAt == null || blockAt.getType() == Material.AIR) return;
        Sign sign = (Sign) blockAt.getState();

        switch (motd) {
            case "LOBBY": {
                sign.setLine(0, format(serverName));
                sign.setLine(1, SignState.LOBBY.name());
                sign.setLine(2, min + "/" + max);
                sign.setLine(3, "CLick");
                break;
            }

            default: {
                sign.setLine(0, format(serverName));
                sign.setLine(1, SignState.LOBBY.name());
                sign.setLine(2, min + "/" + max);
                sign.setLine(3, "Click");
            }

        }
        sign.update();
    }
    private String format(String from) {
        return ChatColor.translateAlternateColorCodes('&', from);
    }

}
