package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerCopyEvent;
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

    //<editor-fold desc="CopyServerCommand">
    public CopyServerCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        if (args.length == 2){
            final String group = args[0];
            final String serverName = args[1];

            if (liptonMaster.getServerGroupConfig().getServerMetaByName(group) != null){
                if (liptonMaster.getServerManager().getServersFromName(serverName) != null){

                    ServerMeta serverMeta = liptonMaster.getServerManager().getServersFromName(serverName);

                    CopyServerPacket serverPacket = new CopyServerPacket(serverMeta.getServerGroupMeta(), serverName, serverMeta.getWrapperID(), serverMeta.getServerGroupMeta().isDynamicService());

                    liptonMaster.getMasterWrapperServer().sendPacket(serverPacket);
                    liptonMaster.getEventManager().callEvent(new ServerCopyEvent(serverMeta));

                    liptonMaster.getCloudConsole().getLogger().info("Trying to copy server '" + serverName + "' into template '" + group + "' !");

                } else {
                    colouredConsoleProvider.getLogger().info("Server not found!");
                }
            }


        } else {
            colouredConsoleProvider.getLogger().info("copy <group> <server>");
        }
        return false;
    }
    //</editor-fold>
}
