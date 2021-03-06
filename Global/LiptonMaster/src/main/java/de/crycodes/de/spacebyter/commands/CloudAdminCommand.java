package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.CloudAdminConfig;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

/**
 * Coded By CryCodes
 * Class: PermsCommand
 * Date : 07.07.2020
 * Time : 12:58
 * Project: LiptonCloud
 */

public class CloudAdminCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="CloudAdminCommand">
    public CloudAdminCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        if (args.length == 1)
            sendUsage(colouredConsoleProvider);
        else if (args.length == 2){
            String identifier = args[0];
            String user = args[1];

            CloudAdminConfig cloudAdminConfig = liptonMaster.getAdminConfig();

            if (identifier.equalsIgnoreCase("check")){
                colouredConsoleProvider.getLogger().info("The user '" + user + "' is admin ? (§c" + cloudAdminConfig.isUserAdmin(user) + "§r)");
            } else if (identifier.equalsIgnoreCase("set")){
                cloudAdminConfig.addPlayer(user);
            } else if (identifier.equalsIgnoreCase("remove")){
                cloudAdminConfig.removePlayer(user);
            } else
                sendUsage(colouredConsoleProvider);

        } else
            sendUsage(colouredConsoleProvider);
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="sendUsage">
    private void sendUsage(CloudConsole colouredConsoleProvider){
        colouredConsoleProvider.getLogger().info("cloudadmin <check> <user>");
        colouredConsoleProvider.getLogger().info("cloudadmin <set> <user>");
        colouredConsoleProvider.getLogger().info("cloudadmin <remove> <user>");
        colouredConsoleProvider.getLogger().info("cloudadmin <help>");
    }
    //</editor-fold>

}
