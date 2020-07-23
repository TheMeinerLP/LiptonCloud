package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerExecuteCommandEvent;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.ExecuteCommandPacket;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ExecuteCommand
 * Date : 27.06.2020
 * Time : 15:54
 * Project: LiptonCloud
 */

public class ExecuteCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ExecuteCommand">
    public ExecuteCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        if (args.length == 0 || args.length == 1){
            colouredConsoleProvider.getLogger().error("execute <server> <command>");
            return true;
        }
        if (liptonMaster.getServerManager().getGlobalServerList().isEmpty()){

            liptonMaster.getCloudConsole().getLogger().error("No Server's found!");
            return true;
        }

        String server = args[0];

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : args){
            if (!s.equalsIgnoreCase(server))
                stringBuilder.append(s + " ");
        }

        liptonMaster.getMasterSpigotServer().sendPacket(new ExecuteCommandPacket(stringBuilder.toString(), server, ReceiverType.SPIGOT));
        liptonMaster.getEventManager().callEvent(new ServerExecuteCommandEvent(server, stringBuilder.toString()));
        colouredConsoleProvider.getLogger().info("Send CommandLine to: " + server);

        return false;
    }
    //</editor-fold>
}
