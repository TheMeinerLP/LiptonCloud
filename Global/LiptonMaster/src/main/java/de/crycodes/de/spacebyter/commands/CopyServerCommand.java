package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.CopyServerPacket;
import org.checkerframework.checker.units.qual.C;

/**
 * Coded By CryCodes
 * Class: CopyServerCommand
 * Date : 07.07.2020
 * Time : 14:11
 * Project: LiptonCloud
 */

public class CopyServerCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    public CopyServerCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        if (args.length == 2){
            final String group = args[0];
            final String serverName = args[1];

            if (liptonMaster.getServerGroupConfig().getServerMetaByName(group) != null){
                if (liptonMaster.getServerManager().getServersFromName(serverName) != null){

                    ServerMeta serverMeta = liptonMaster.getServerManager().getServersFromName(serverName);

                    CopyServerPacket serverPacket = new CopyServerPacket(serverMeta.getServerGroupMeta(), serverName, serverMeta.getWrapperID(), serverMeta.getServerGroupMeta().isDynamicService());

                    liptonMaster.getMasterWrapperServer().sendPacket(serverPacket);

                    liptonMaster.getColouredConsoleProvider().info("Trying to copy server '" + serverName + "' into template '" + group + "' !");

                } else {
                    colouredConsoleProvider.info("Server not found!");
                }
            }


        } else {
            colouredConsoleProvider.info("copy <group> <server>");
        }
        return false;
    }
}
