package de.crycodes.de.spacebyter.serverhelper;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.SendProxyConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.SendServerConfigPacket;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Coded By CryCodes
 * Class: PorxyConfigHandler
 * Date : 26.06.2020
 * Time : 14:15
 * Project: LiptonCloud
 */

public class ConfigHandler {

    private final LiptonMaster liptonMaster;
    private final Scheduler scheduler;

    public ConfigHandler(LiptonMaster liptonMaster, Scheduler scheduler) {
        this.liptonMaster = liptonMaster;
        this.scheduler = scheduler;
    }

    @ShouldRunAsync
    public ConfigHandler startUpdateThread(){
        scheduler.scheduleAsyncWhile(new Runnable() {
            @Override
            public void run() {

                liptonMaster.getProxyFileConfig().load();
                liptonMaster.getMasterConfig().reload();

                ProxyConfig proxyConfig = new ProxyConfig(liptonMaster.getServerManager().getGlobalserverrlist(),
                        liptonMaster.getWrapperManager().getWrapperList(),
                        liptonMaster.getProxyManager().getGlobalProxyList(),
                        liptonMaster.getMasterConfig().isMaintenance(),
                        Arrays.asList(liptonMaster.getMasterConfig().getWhiteListed()),
                        liptonMaster.getServerGroupConfig().getServerMetas(),
                        new ArrayList<String>() /*TODO CLOUD ADMIN LIST*/,
                        liptonMaster.getProxyFileConfig().getTablist_top(),
                        liptonMaster.getProxyFileConfig().getTablist_bottom(),
                        liptonMaster.getProxyFileConfig().getDefault_motd(),
                        liptonMaster.getProxyFileConfig().getMaintenance_motd(),
                        liptonMaster.getProxyFileConfig().getMaintenanceVersionString(),
                        liptonMaster.getProxyFileConfig().getMaxPlayer(),
                        liptonMaster.getProxyFileConfig().getUseProxyConfig(),
                        liptonMaster.getProxyFileConfig().getMaintenanceKickMessage(),
                        liptonMaster.getProxyFileConfig().getServer_start_message(),
                        liptonMaster.getProxyFileConfig().getServer_stop_message(),
                        liptonMaster.getProxyFileConfig().getServer_online_message(),
                        liptonMaster.getProxyFileConfig().getUseNotify());

                ServerConfig serverConfig = new ServerConfig(liptonMaster.getServerGlobalManager().getGlobalServerList(),
                        liptonMaster.getWrapperManager().getWrapperList(),
                        liptonMaster.getProxyManager().getGlobalProxyList(),
                        liptonMaster.getServerGroupConfig().getServerMetas(),
                        Arrays.asList(liptonMaster.getMasterConfig().getWhiteListed())
                        );

                liptonMaster.getMasterSpigotServer().getServer().sendPacket(
                        liptonMaster.getMasterSpigotServer().getNetworkChannel(),
                        new SendServerConfigPacket(serverConfig, "ALL"));

                if (liptonMaster.getMasterConfig().isDebugMode())
                    liptonMaster.getColouredConsoleProvider().debug("Send ServerConfig!");

                liptonMaster.getMasterProxyServer().getServer().sendPacket(
                        liptonMaster.getMasterProxyServer().getNetworkChannel(),
                        new SendProxyConfigPacket(proxyConfig, "ALL"));

                if (liptonMaster.getMasterConfig().isDebugMode())
                    liptonMaster.getColouredConsoleProvider().debug("Send ProxyConfig!");


            }
        }, 2500, 2500);

        return this;
    }


}
