package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
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
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        if (args.length == 1){

            switch (args[0]){

                case "servers": {
                    liptonMaster.getColouredConsoleProvider().info("SERVERS (ONLINE) : ");
                    for (ServerMeta serverMeta : liptonMaster.getServerManager().getOnlineServers()){
                        liptonMaster.getColouredConsoleProvider().sendMessageWithCustomPrefix("§aONLINE: §r", "NAME: "+ serverMeta.getServerName() + " | GROUP: " + serverMeta.getServerGroupMeta().getGroupName() + " | TEMPLATE: " + serverMeta.getServerGroupMeta().getTemplate());
                    }
                    liptonMaster.getColouredConsoleProvider().info("SERVERS (STARTING) : ");
                    for (ServerMeta serverMeta : liptonMaster.getServerManager().getStartedServers()){
                        liptonMaster.getColouredConsoleProvider().sendMessageWithCustomPrefix("§aSTARTING: §r", "NAME: "+ serverMeta.getServerName() + " | GROUP: " + serverMeta.getServerGroupMeta().getGroupName() + " | TEMPLATE: " + serverMeta.getServerGroupMeta().getTemplate());
                    }

                    break;
                }
                case "groups": {
                    liptonMaster.getColouredConsoleProvider().info("GROUPS: ");
                    for (ServerGroupMeta serverGroupMeta : liptonMaster.getServerGroupConfig().getServerMetas()){
                        liptonMaster.getColouredConsoleProvider().sendMessageWithCustomPrefix("§eGOURPS: §r", "NAME: " + serverGroupMeta.getGroupName() + " | TEMPLATE: " + serverGroupMeta.getTemplate());
                    }

                    break;
                }
                case "wrappers": {
                    liptonMaster.getColouredConsoleProvider().info("WRAPPERS: ");
                    for (WrapperMeta wrapperMeta : liptonMaster.getWrapperManager().getWrapperList()){
                        liptonMaster.getColouredConsoleProvider().sendMessageWithCustomPrefix("§eWRAPPER: §r", "WRAPPER-ID: " + wrapperMeta.getWrapperConfig().getWrapperId() + " | HOST: " + wrapperMeta.getWrapperConfig().getHost() );
                    }

                    break;
                }
                case "proxys": {
                    liptonMaster.getColouredConsoleProvider().info("PROXYS: ");
                    for (ProxyMeta proxyMeta : liptonMaster.getProxyManager().getGlobalProxyList()){
                        liptonMaster.getColouredConsoleProvider().sendMessageWithCustomPrefix("§ePROXY: §r" , "NAME: " + proxyMeta.getName() + " | SERVERS RUNNING ON PROXY: " + proxyMeta.getServerlist().size());
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

    public void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("info <servers>");
        colouredConsoleProvider.info("info <groups>");
        colouredConsoleProvider.info("info <wrappers>");
        colouredConsoleProvider.info("info <proxys>");
    }

}
