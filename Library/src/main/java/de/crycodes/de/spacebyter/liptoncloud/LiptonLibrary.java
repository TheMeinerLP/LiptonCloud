package de.crycodes.de.spacebyter.liptoncloud;

import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.RegisterResponsePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.ShutDownPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ProxyStoppingPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.*;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerStoppingPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CreateTemplatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.DebugPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.ErrorPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.InfoPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.WarningPacket;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.AsciiPrinter;
import de.crycodes.de.spacebyter.network.channel.Identifier;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.channel.Provider;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;

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
    }

    public void printAscii(){
        new AsciiPrinter().Print(this.colouredConsoleProvider, this.useColor);
    }

    public void registerPacket(PacketHandler packetHandler){
        packetHandler.registerPacket((byte) 0, RegisterPacket.class);
        packetHandler.registerPacket((byte) 1, ShutDownPacket.class);
        packetHandler.registerPacket((byte) 2, SendProxyConfigPacket.class);
        packetHandler.registerPacket((byte) 3, StopProxyPacket.class);
        packetHandler.registerPacket((byte) 4, ProxyStoppingPacket.class);
        packetHandler.registerPacket((byte) 6, ExecuteCommandPacket.class);
        packetHandler.registerPacket((byte) 7, SendServerConfigPacket.class);
        packetHandler.registerPacket((byte) 8, StopServerGroupPacket.class);
        packetHandler.registerPacket((byte) 9, StopServerPacket.class);
        packetHandler.registerPacket((byte) 10, ServerStoppingPacket.class);
        packetHandler.registerPacket((byte) 11, CopyServerPacket.class);
        packetHandler.registerPacket((byte) 12, CreateTemplatePacket.class);
        packetHandler.registerPacket((byte) 13, StartServerPacket.class);
        packetHandler.registerPacket((byte) 14, DebugPacket.class);
        packetHandler.registerPacket((byte) 15, ErrorPacket.class);
        packetHandler.registerPacket((byte) 16, InfoPacket.class);
        packetHandler.registerPacket((byte) 17, WarningPacket.class);
        packetHandler.registerPacket((byte) 18, UpdateMaintenancePacket.class);
        packetHandler.registerPacket((byte) 19, ServerGroupUpdatePacket.class);
        packetHandler.registerPacket((byte) 20, ServerUpdatePacket.class);
        packetHandler.registerPacket((byte) 21, RegisterResponsePacket.class);
        packetHandler.getRegisterdpackets().forEach((aByte, aClass) -> {
            this.colouredConsoleProvider.info("Registered Packet By Clazz: " + aClass.getCanonicalName());
        });
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
