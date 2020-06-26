package de.crycodes.de.spacebyter.liptonbridge.bungeecord;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.bungeecord.listener.PlayerConnectEvent;
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

    private final String PREFIX = "§bCloud §7✸ ";

    @Override
    public void onEnable() {

        cloudAPI = new CloudAPI(false);

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
}
