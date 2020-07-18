package de.crycodes.de.spacebyter.liptonbridge.bungeecord;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands.CloudCommand;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands.HubCommand;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands.ServerCommand;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.listener.PlayerConnectEvent;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.manager.HubManager;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.BungeeMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import java.util.ArrayList;
import java.util.List;


/**
 * Coded By CryCodes
 * Class: LiptonBungeeBridge
 * Date : 25.06.2020
 * Time : 20:56
 * Project: LiptonCloud
 */

public class LiptonBungeeBridge extends Plugin {

    private CloudAPI cloudAPI;
    private BungeeMasterClient bungeeMasterClient;
    private LiptonLibrary liptonLibrary;
    private HubManager hubManager;

    //INSTANCE Cache
    private final List<ProxyConfig> proxyConfig = new ArrayList<>();
    //INSTANCE Cache


    private static LiptonBungeeBridge instance;
    private final String PREFIX = "§8┃ §b§lLipton §7× ";


    @Override
    public void onEnable() {
        instance = this;
        cloudAPI = new CloudAPI(false);

        hubManager = new HubManager();

        ProxyServer.getInstance().getPluginManager().registerCommand(this , new HubCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this , new ServerCommand());
        ProxyServer.getInstance().getPluginManager().registerListener(this, hubManager);

        bungeeMasterClient = new BungeeMasterClient("127.0.0.1", 1784).start();

        new CloudCommand(this);
        new PlayerConnectEvent(this);
    }

    //<editor-fold desc="onDisable">
    @Override
    public void onDisable() {
        super.onDisable();
    }
    //</editor-fold>

    //<editor-fold desc="getProxyConfig">
    public ProxyConfig getProxyConfig(){
        if (proxyConfig.isEmpty()){
            System.out.println("NO CONFIG FOUND");
                return new ProxyConfig(new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        false,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        "\n§b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n§7Players: {PLAYERS}",
                        "\n§7Server: {SERVER}",
                        "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bAvailable §7✸ §7We are available.",
                        "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bMaintenance §7✸ §7We are in maintenance mode."
                        ,"§7[§bWartungsmodus§7]",
                        50,
                        true,
                        "§bLipton Cloud\n§7We are in maintenance mode", "server_start_message", "server_stop_message", "server_online_message", false);
            } else {
                return proxyConfig.iterator().next();
            }

    }
    //</editor-fold>

    //<editor-fold desc="updateConfig">
    public void updateConfig(ProxyConfig proxyConfig){
        this.proxyConfig.clear();
        this.proxyConfig.add(proxyConfig);
    }
    //</editor-fold>

    //<editor-fold desc="getCloudAPI">
    public CloudAPI getCloudAPI() {
        return cloudAPI;
    }
    //</editor-fold>

    //<editor-fold desc="getPREFIX">
    public String getPREFIX() {
        return PREFIX;
    }
    //</editor-fold>

    //<editor-fold desc="getBungeeMasterClient">
    public BungeeMasterClient getBungeeMasterClient() {
        return bungeeMasterClient;
    }
    //</editor-fold>

    //<editor-fold desc="getInstance">
    public static LiptonBungeeBridge getInstance() {
        return instance;
    }
    //</editor-fold>

    //<editor-fold desc="getHubManager">
    public HubManager getHubManager() {
        return hubManager;
    }
    //</editor-fold>

}
