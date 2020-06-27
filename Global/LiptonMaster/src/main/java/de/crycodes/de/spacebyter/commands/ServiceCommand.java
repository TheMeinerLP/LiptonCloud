package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.in.StopProxyPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;

public class ServiceCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    public ServiceCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }


    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        if (args.length == 2){

            final String name = args[1];

            if (args[0].equalsIgnoreCase("stopserver")){

                if (liptonMaster.getServerGlobalManager().getGlobalServerList().isEmpty()){

                    liptonMaster.getColouredConsoleProvider().error("No Server's found!");
                    return true;
                }

                liptonMaster.getMasterSpigotServer().sendPacket(new StopServerPacket(name));

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
                        liptonMaster.getMasterSpigotServer().sendPacket(new StopServerGroupPacket(serverGroupMeta, ExitUtil.STOPPED_SUCESS));
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

    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("service stopserver name");
        colouredConsoleProvider.info("service stopproxy name");
        colouredConsoleProvider.info("service stopgroup group");

    }

}
