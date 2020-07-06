package de.crycodes.de.spacebyter.liptonbridge.spigot;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.spigot.commands.CreateSignCommand;
import de.crycodes.de.spacebyter.liptonbridge.spigot.listeners.ClickListener;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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

    private CloudSignManager cloudSignManager;

    //INSTANCE Cache
    private List<ServerConfig> serverConfig = new ArrayList<>();
    //INSTANCE Cache

    @Override
    public void onEnable() {
        instance = this;

        cloudAPI = new CloudAPI(true);

        cloudAPI.getServerMeta();

        spigotMasterClient = new SpigotMasterClient("127.0.0.1", 7898).start();

        cloudSignManager = new CloudSignManager();

         getCommand("createsign").setExecutor(new CreateSignCommand());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getPluginManager().registerEvents(new ClickListener(), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {

        ServerMeta serverMeta = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta();

        spigotMasterClient.getThunderClient().sendPacket(spigotMasterClient.getNetworkChannel(), new ServerStoppingPacket(serverMeta));

        Document document;
        File metaFile = new File("./META.json");
        document = Document.loadDocument(metaFile);
        System.out.println("LOADED META");


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

    public ServerConfig getServerConfig() {
        return this.serverConfig.iterator().next();
    }

    public CloudSignManager getCloudSignManager() {
        return cloudSignManager;
    }
}
