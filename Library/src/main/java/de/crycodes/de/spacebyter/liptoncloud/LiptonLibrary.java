package de.crycodes.de.spacebyter.liptoncloud;

import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
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

import java.io.File;

/**
 * Coded By CryCodes
 * Class: LiptonLibrary
 * Date : 24.06.2020
 * Time : 10:25
 * Project: LiptonCloud
 */

public class LiptonLibrary {

    private static LiptonLibrary instance;

    private  Scheduler scheduler;
    private  Boolean useColor;

    public LiptonLibrary(Scheduler scheduler, Boolean useColor ) {
        this.scheduler = scheduler;
        this.useColor = useColor;
        instance = this;

    }
    public void checkAPIFile(File file){
        if (file.exists()) return;
        System.out.println("NO LiptonBridge found! please add API to api folder!");
        System.exit(ExitState.STOPPED_SUCESS.getState());
    }

    public LiptonLibrary() { }

    public void printAscii(CloudConsole cloudConsole){
        new AsciiPrinter().Print(cloudConsole, this.useColor);
    }

    public void registerPacket(PacketHandler packetHandler, CloudConsole cloudConsole){
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
                cloudConsole.getLogger().info("Registered Packet By Clazz: " + aClass.getCanonicalName());
        });
    }

    private NetworkChannel CloudChannel = new NetworkChannel(new Identifier("Master_Wrapper"), new Provider("LIPTON"));

    public static void setInstance(LiptonLibrary instance) {
        LiptonLibrary.instance = instance;
    }

    public static LiptonLibrary getInstance() {
        return instance;
    }


    public NetworkChannel getCloudChannel() {
        return CloudChannel;
    }
}
