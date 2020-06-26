package de.crycodes.de.spacebyter.liptonbridge.spigot;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import org.bukkit.plugin.java.JavaPlugin;

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

    private static LiptonSpigotBridge instance;

    //INSTANCE Cache
    private ServerConfig serverConfig;
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
        super.onDisable();
    }

    public CloudAPI getCloudAPI() {
        return cloudAPI;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public static LiptonSpigotBridge getInstance() {
        return instance;
    }

    public SpigotMasterClient getSpigotMasterClient() {
        return spigotMasterClient;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }
}