package de.crycodes.de.spacebyter.liptonbridge.spigot;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands.CloudCommand;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: LiptonSpigotBridge
 * Date : 25.06.2020
 * Time : 20:56
 * Project: LiptonCloud
 */

public class LiptonSpigotBridge extends JavaPlugin {

    private CloudAPI cloudAPI;
    private SpigotMasterClient spigotMasterClient;
    private LiptonLibrary liptonLibrary;

    private static LiptonSpigotBridge instance;

    //INSTANCE Cache
    private List<ServerConfig> serverConfig = new ArrayList<>();
    //INSTANCE Cache

    @Override
    public void onEnable() {
        instance = this;

        cloudAPI = new CloudAPI(true);

        spigotMasterClient = new SpigotMasterClient("127.0.0.1", 7898).start();

        super.onEnable();
    }

    @Override
    public void onDisable() {

        ServerMeta serverMeta;
        if (LiptonSpigotBridge.getInstance().getCloudAPI().getProxyMeta() == null)
            serverMeta = new ServerMeta("NONE", 1, new ServerGroupMeta("NONE", 512, 128, false,false, 0,0), "NONE", "127.0.0.1", 0);
        else
            serverMeta = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta();

        spigotMasterClient.getThunderClient().sendPacket(spigotMasterClient.getNetworkChannel(), new ServerStoppingPacket(serverMeta));
    }

    public CloudAPI getCloudAPI() {
        return cloudAPI;
    }

    public static LiptonSpigotBridge getInstance() {
        return instance;
    }

    public SpigotMasterClient getSpigotMasterClient() {
        return spigotMasterClient;
    }

    public void updateConfig(ServerConfig serverConfig){
        this.serverConfig.clear();
        this.serverConfig.add(serverConfig);
    }

    public List<ServerConfig> getServerConfig() {
        return serverConfig;
    }
}
