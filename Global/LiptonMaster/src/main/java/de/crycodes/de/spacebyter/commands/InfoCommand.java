package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

/**
 * Coded By CryCodes
 * Class: InfoCommand
 * Date : 19.07.2020
 * Time : 15:06
 * Project: LiptonCloud
 */

public class InfoCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    public InfoCommand(String name, String description, LiptonMaster liptonMaster, String... aliases) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }

    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        if (args.length == 1){

            switch (args[0]){

                case "servers": {
                    liptonMaster.getCloudConsole().getLogger().info("SERVERS (ONLINE) : ");
                    for (ServerMeta serverMeta : liptonMaster.getServerManager().getOnlineServers()){
                        liptonMaster.getCloudConsole().getLogger().info("NAME: "+ serverMeta.getServerName() + " | GROUP: " + serverMeta.getServerGroupMeta().getGroupName() + " | TEMPLATE: " + serverMeta.getServerGroupMeta().getTemplate());
                    }
                    liptonMaster.getCloudConsole().getLogger().info("SERVERS (STARTING) : ");
                    for (ServerMeta serverMeta : liptonMaster.getServerManager().getStartedServers()){
                        liptonMaster.getCloudConsole().getLogger().info("NAME: "+ serverMeta.getServerName() + " | GROUP: " + serverMeta.getServerGroupMeta().getGroupName() + " | TEMPLATE: " + serverMeta.getServerGroupMeta().getTemplate());
                    }

                    break;
                }
                case "groups": {
                    liptonMaster.getCloudConsole().getLogger().info("GROUPS: ");
                    for (ServerGroupMeta serverGroupMeta : liptonMaster.getServerGroupConfig().getServerMetas()){
                        liptonMaster.getCloudConsole().getLogger().info("NAME: " + serverGroupMeta.getGroupName() + " | TEMPLATE: " + serverGroupMeta.getTemplate());
                    }

                    break;
                }
                case "wrappers": {
                    liptonMaster.getCloudConsole().getLogger().info("WRAPPERS: ");
                    for (WrapperMeta wrapperMeta : liptonMaster.getWrapperManager().getWrapperList()){
                        liptonMaster.getCloudConsole().getLogger().info("WRAPPER-ID: " + wrapperMeta.getWrapperConfig().getWrapperId() + " | HOST: " + wrapperMeta.getWrapperConfig().getHost() );
                    }

                    break;
                }
                case "proxys": {
                    liptonMaster.getCloudConsole().getLogger().info("PROXYS: ");
                    for (ProxyMeta proxyMeta : liptonMaster.getProxyManager().getGlobalProxyList()){
                        liptonMaster.getCloudConsole().getLogger().info("NAME: " + proxyMeta.getName() + " | SERVERS RUNNING ON PROXY: " + proxyMeta.getServerlist().size());
                    }

                    break;
                }
                default: {
                    sendUsage(colouredConsoleProvider);
                }

            }

        } else {
            sendUsage(colouredConsoleProvider);
        }
        return false;
    }

    public void sendUsage(CloudConsole colouredConsoleProvider){
        colouredConsoleProvider.getLogger().info("info <servers>");
        colouredConsoleProvider.getLogger().info("info <groups>");
        colouredConsoleProvider.getLogger().info("info <wrappers>");
        colouredConsoleProvider.getLogger().info("info <proxys>");
    }

}
