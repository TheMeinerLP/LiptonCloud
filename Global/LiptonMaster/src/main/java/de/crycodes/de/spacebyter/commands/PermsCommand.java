package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.CloudAdminConfig;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: PermsCommand
 * Date : 07.07.2020
 * Time : 12:58
 * Project: LiptonCloud
 */

public class PermsCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    public PermsCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        if (args.length == 1)
            sendUsage(colouredConsoleProvider);
        else if (args.length == 2){
            String identifier = args[0];
            String user = args[1];

            CloudAdminConfig cloudAdminConfig = liptonMaster.getAdminConfig();

            if (identifier.equalsIgnoreCase("check")){
                colouredConsoleProvider.info("The user '" + user + "' is admin ? (§c" + cloudAdminConfig.isUserAdmin(user) + "§r)");
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

    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("perms <check> <user>");
        colouredConsoleProvider.info("perms <set> <user>");
        colouredConsoleProvider.info("perms <remove> <user>");
        colouredConsoleProvider.info("perms <help>");
    }

}
