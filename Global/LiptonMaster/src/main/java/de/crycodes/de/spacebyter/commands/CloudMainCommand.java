package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
import de.crycodes.de.spacebyter.liptoncloud.events.ProxyStopEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerGroupStopEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerStopEvent;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;

import java.io.File;


public class CloudMainCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ServiceCommand">
    public CloudMainCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>


    //<editor-fold desc="execute">
    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        if (args.length == 2){

            final String name = args[1];

            if (args[0].equalsIgnoreCase("stopserver")){

                if (liptonMaster.getServerManager().getGlobalServerList().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Server's found!");
                    return true;
                }

                if (liptonMaster.getServerManager().getServersFromName(name) == null)
                    return true;

                ServerMeta serverMeta = liptonMaster.getServerManager().getServersFromName(name);

                liptonMaster.getMasterSpigotServer().sendPacket(new StopServerPacket(name, serverMeta.getWrapperID(), serverMeta.getServerGroupMeta().isDynamicService()));
                liptonMaster.getEventManager().callEvent(new ServerStopEvent(serverMeta));

                liptonMaster.getColouredConsoleProvider().info("Send StopPacket to Server: " + name);

                return true;

            }

            if (args[0].equalsIgnoreCase("stopproxy")){

                if (liptonMaster.getProxyManager().getGlobalProxyList().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Proxy's found!");
                    return true;
                }

                ProxyMeta proxyMeta = new ProxyMeta(name, 0, false);
                liptonMaster.getMasterProxyServer().getServer().sendPacket(liptonMaster.getMasterProxyServer().getNetworkChannel(),new StopProxyPacket(proxyMeta));
                liptonMaster.getEventManager().callEvent(new ProxyStopEvent(proxyMeta));

                liptonMaster.getColouredConsoleProvider().info("Send StopPacket to  Proxy: " + name);

                return true;
            }

            if (args[0].equalsIgnoreCase("stopgroup")){

                if (liptonMaster.getServerGroupConfig().getServerMetas().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Server Group found!");
                    return true;
                }

                liptonMaster.getServerGroupConfig().getServerMetas().forEach(serverGroupMeta -> {
                    if (serverGroupMeta.getGroupName().equalsIgnoreCase(name)){
                        liptonMaster.getMasterSpigotServer().sendPacket(new StopServerGroupPacket(serverGroupMeta, ExitState.STOPPED_SUCESS.getState()));
                        liptonMaster.getEventManager().callEvent(new ServerGroupStopEvent(serverGroupMeta));
                        liptonMaster.getColouredConsoleProvider().info("Send StopServerGroup to All Server's of Group: " + name);
                        return;
                    }
                    liptonMaster.getColouredConsoleProvider().error("Server Group: " + name + " was not found!");
                });

                return true;
            }

            if (args[0].equalsIgnoreCase("delete")){


                if (liptonMaster.getServerGroupConfig().getServerMetas().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Server Group found!");
                    return true;
                }

                if (liptonMaster.getServerGroupConfig().getServerMetaByName(name) != null){



                    if (name.equalsIgnoreCase("Lobby")) {
                        liptonMaster.getColouredConsoleProvider().error("Could not delete ServerGroup: " + name);
                        return true;
                    }

                    if ((new File("./liptonMaster/groups/server/" + name + ".json")).delete()){
                        liptonMaster.getColouredConsoleProvider().info("Deleted ServerGroup: " +  name);
                        return true;
                    }

                    liptonMaster.getColouredConsoleProvider().error("Could not delete ServerGroup: " + name);

                    return true;
                }

                liptonMaster.getColouredConsoleProvider().error("Server Group: " + name + " was not found!");

                return true;
            }

            sendUsage(colouredConsoleProvider);

        } else {


            sendUsage(colouredConsoleProvider);

        }

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="sendUsage">
    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("cloud <stopserver> <name>");
        colouredConsoleProvider.info("cloud <stopproxy> <name>");
        colouredConsoleProvider.info("cloud <stopgroup> <group>");
        colouredConsoleProvider.info("cloud <delete> <servergroup>");

    }
    //</editor-fold>

}
