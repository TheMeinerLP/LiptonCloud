package de.crycodes.de.spacebyter.liptonbridge.bungeecord;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.listener.PlayerConnectEvent;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.networking.BungeeMasterClient;
import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import net.md_5.bungee.api.plugin.Plugin;


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

    //INSTANCE Cache
    private ProxyConfig proxyConfig;
    //INSTANCE Cache


    private static LiptonBungeeBridge instance;
    private final String PREFIX = "§bCloud §7✸ ";


    @Override
    public void onEnable() {
        instance = this;
        cloudAPI = new CloudAPI(false);

        bungeeMasterClient = new BungeeMasterClient("127.0.0.1", 1784).start();

        new PlayerConnectEvent(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public CloudAPI getCloudAPI() {
        return cloudAPI;
    }

    public String getPREFIX() {
        return PREFIX;
    }

    public BungeeMasterClient getBungeeMasterClient() {
        return bungeeMasterClient;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    public void setProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    public static LiptonBungeeBridge getInstance() {
        return instance;
    }
}
