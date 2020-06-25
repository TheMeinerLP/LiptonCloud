package de.crycodes.de.spacebyter.liptoncloud;

import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.AsciiPrinter;
import de.crycodes.de.spacebyter.network.channel.Identifier;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.channel.Provider;

/**
 * Coded By CryCodes
 * Class: LiptonLibrary
 * Date : 24.06.2020
 * Time : 10:25
 * Project: LiptonCloud
 */

public class LiptonLibrary {

    private static LiptonLibrary instance;

    private final Scheduler scheduler;
    private final EventManager eventManager;
    private final ColouredConsoleProvider colouredConsoleProvider;
    private final AddonParallelLoader addonParallelLoader;
    private final Boolean useColor;

    public LiptonLibrary(Scheduler scheduler, EventManager eventManager, ColouredConsoleProvider colouredConsoleProvider, AddonParallelLoader addonParallelLoader, Boolean useColor) {
        this.scheduler = scheduler;
        this.eventManager = eventManager;
        this.colouredConsoleProvider = colouredConsoleProvider;
        this.addonParallelLoader = addonParallelLoader;
        this.useColor = useColor;
        instance = this;
        new AsciiPrinter().Print(colouredConsoleProvider, useColor);
    }

    private NetworkChannel Master_Wrapper_Channel = new NetworkChannel(new Identifier("Master_Wrapper"), new Provider("LIPTON"));
    private NetworkChannel SignSystem_Channel = new NetworkChannel(new Identifier("SignSystem"), new Provider("LIPTON"));
    private NetworkChannel Spigot_Proxy_Channel = new NetworkChannel(new Identifier("Spigot_Proxy"), new Provider("LIPTON"));
    private NetworkChannel Proxy_Master_Channel = new NetworkChannel(new Identifier("Proxy_Master"), new Provider("LIPTON"));
    private NetworkChannel Spigot_Master_Channel = new NetworkChannel(new Identifier("Spigot_Master"), new Provider("LIPTON"));

    public static void setInstance(LiptonLibrary instance) {
        LiptonLibrary.instance = instance;
    }

    public NetworkChannel getMaster_Wrapper_Channel() {
        return Master_Wrapper_Channel;
    }

    public void setMaster_Wrapper_Channel(NetworkChannel master_Wrapper_Channel) {
        Master_Wrapper_Channel = master_Wrapper_Channel;
    }

    public NetworkChannel getSignSystem_Channel() {
        return SignSystem_Channel;
    }

    public void setSignSystem_Channel(NetworkChannel signSystem_Channel) {
        SignSystem_Channel = signSystem_Channel;
    }

    public NetworkChannel getSpigot_Proxy_Channel() {
        return Spigot_Proxy_Channel;
    }

    public void setSpigot_Proxy_Channel(NetworkChannel spigot_Proxy_Channel) {
        Spigot_Proxy_Channel = spigot_Proxy_Channel;
    }

    public NetworkChannel getProxy_Master_Channel() {
        return Proxy_Master_Channel;
    }

    public void setProxy_Master_Channel(NetworkChannel proxy_Master_Channel) {
        Proxy_Master_Channel = proxy_Master_Channel;
    }

    public NetworkChannel getSpigot_Master_Channel() {
        return Spigot_Master_Channel;
    }

    public void setSpigot_Master_Channel(NetworkChannel spigot_Master_Channel) {
        Spigot_Master_Channel = spigot_Master_Channel;
    }

    public static LiptonLibrary getInstance() {
        return instance;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public AddonParallelLoader getAddonParallelLoader() {
        return addonParallelLoader;
    }

    public Boolean getUseColor() {
        return useColor;
    }
}
