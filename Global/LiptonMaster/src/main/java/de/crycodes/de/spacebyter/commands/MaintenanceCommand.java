package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

/**
 * Coded By CryCodes
 * Class: MaintenaceCommand
 * Date : 07.07.2020
 * Time : 13:26
 * Project: LiptonCloud
 */

public class MaintenanceCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="MaintenanceCommand">
    public MaintenanceCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {

        if (args.length == 1){
            if (args[0].equalsIgnoreCase("toggle")){

                if (liptonMaster.getMasterConfig().switchMaintenance()){
                    colouredConsoleProvider.getLogger().info("The Network is now in §aMaintenance §7Mode.");
                } else {
                    colouredConsoleProvider.getLogger().info("The Network is no longer in §cMaintenance §7Mode.");
                }

            } else

                sendUsage(colouredConsoleProvider);

        } else if (args.length == 2){

            String identifier = args[0];
            String user = args[1];

            if (identifier.equalsIgnoreCase("add")){

                liptonMaster.getMaintenanceConfig().addPlayer(user);

            } else if (identifier.equalsIgnoreCase("remove")){

                liptonMaster.getMaintenanceConfig().removePlayer(user);

            } else

                sendUsage(colouredConsoleProvider);

        } else

            sendUsage(colouredConsoleProvider);

        return false;
    }
    //</editor-fold>

    //<editor-fold desc="sendUsage">
    private void sendUsage(CloudConsole colouredConsoleProvider){
        colouredConsoleProvider.getLogger().info("maintenance <add> <user>");
        colouredConsoleProvider.getLogger().info("maintenance <remove> <user>");
        colouredConsoleProvider.getLogger().info("maintenance <toggle>");
    }
    //</editor-fold>
}
