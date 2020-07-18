package de.crycodes.de.spacebyter.liptonbridge.spigot;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.spigot.commands.CreateSignCommand;
import de.crycodes.de.spacebyter.liptonbridge.spigot.enums.SignState;
import de.crycodes.de.spacebyter.liptonbridge.spigot.listeners.PlayerInteractEvents;
import de.crycodes.de.spacebyter.liptonbridge.spigot.networking.SpigotMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    private final String PREFIX = "§8┃ §b§lLipton §7× ";

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

        new PlayerInteractEvents(this);

        super.onEnable();
    }

    @Override
    public void onDisable() {

        ServerMeta serverMeta = LiptonSpigotBridge.getInstance().getCloudAPI().getServerMeta();
        spigotMasterClient.getThunderClient().sendPacket(spigotMasterClient.getNetworkChannel(), new ServerStoppingPacket(serverMeta));

        if(getServer().getMessenger().isOutgoingChannelRegistered(this, "BungeeCord"))
            getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }

    //<editor-fold desc="setMotd">
    public void setMotd(SignState signState)  {
        String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        Object minecraftserver = null;
        try {
            minecraftserver = Class.forName("org.bukkit.craftbukkit."+bukkitversion+".CraftServer").getDeclaredMethod("getServer", null).invoke(Bukkit.getServer(), null);
            Field f = minecraftserver.getClass().getSuperclass().getDeclaredField("motd");
            f.setAccessible(true);
            f.set(minecraftserver, signState.name());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
    //</editor-fold>

    //<editor-fold desc="getCloudAPI">
    public CloudAPI getCloudAPI() {
        return cloudAPI;
    }
    //</editor-fold>

    //<editor-fold desc="getInstance">
    public static LiptonSpigotBridge getInstance() {
        return instance;
    }
    //</editor-fold>

    //<editor-fold desc="getSpigotMasterClient">
    public SpigotMasterClient getSpigotMasterClient() {
        return spigotMasterClient;
    }
    //</editor-fold>

    //<editor-fold desc="updateConfig">
    public void updateConfig(ServerConfig serverConfig){
        this.serverConfig.clear();
        this.serverConfig.add(serverConfig);
    }
    //</editor-fold>

    //<editor-fold desc="getServerConfig">
    public ServerConfig getServerConfig() {
        return this.serverConfig.iterator().next();
    }
    //</editor-fold>

    //<editor-fold desc="getCloudSignManager">
    public CloudSignManager getCloudSignManager() {
        return cloudSignManager;
    }
    //</editor-fold>

    //<editor-fold desc="getPREFIX">
    public String getPREFIX() {
        return PREFIX;
    }
    //</editor-fold>

}
