package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;


public class ScreenCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ServiceCommand">
    public ScreenCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
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

                liptonMaster.getColouredConsoleProvider().info("Send StopPacket to Server: " + name);

                return true;

            }

            if (args[0].equalsIgnoreCase("stopproxy")){

                if (liptonMaster.getProxyManager().getGlobalProxyList().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Proxy's found!");
                    return true;
                }

                liptonMaster.getMasterProxyServer().getServer().sendPacket(liptonMaster.getMasterProxyServer().getNetworkChannel(),new StopProxyPacket(new ProxyMeta(name, 0, false)));

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
                        liptonMaster.getColouredConsoleProvider().info("Send StopServerGroup to All Server's of Group: " + name);
                        return;
                    }
                    liptonMaster.getColouredConsoleProvider().error("Server Group: " + name + " was not found!");
                });

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
        colouredConsoleProvider.info("screen <stopserver> <name>");
        colouredConsoleProvider.info("screen <stopproxy> <name>");
        colouredConsoleProvider.info("screen <stopgroup> <group>");

    }
    //</editor-fold>

}
