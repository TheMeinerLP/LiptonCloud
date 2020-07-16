package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.util.Arrays;

/**
 * Coded By CryCodes
 * Class: HelpCommand
 * Date : 24.06.2020
 * Time : 10:49
 * Project: LiptonCloud
 */

public class HelpCommand extends CloudCommand {

    private final LiptonMaster master;

    //<editor-fold desc="HelpCommand">
    public HelpCommand(String name, String description, String[] aliases, LiptonMaster master) {
        super(name, description, aliases);
        this.master = master;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        colouredConsoleProvider.info("Help Command:");

        colouredConsoleProvider.info("copy <group> <server>");
        colouredConsoleProvider.info("create <SERVERGROUP>");
        colouredConsoleProvider.info("create <WRAPPER>");
        colouredConsoleProvider.info("execute <server> <command>");
        colouredConsoleProvider.info("maintenance <add> <user>");
        colouredConsoleProvider.info("maintenance <remove> <user>");
        colouredConsoleProvider.info("maintenance <toggle>");
        colouredConsoleProvider.info("perms <check> <user>");
        colouredConsoleProvider.info("perms <set> <user>");
        colouredConsoleProvider.info("perms <remove> <user>");
        colouredConsoleProvider.info("perms <help>");
        colouredConsoleProvider.info("service <stopserver> <name>");
        colouredConsoleProvider.info("service <stopproxy> <name>");
        colouredConsoleProvider.info("service <stopgroup> <group>");
        colouredConsoleProvider.info("reload");
        colouredConsoleProvider.info("stop");


        return false;
    }
    //</editor-fold>
}
