package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.LiptonMasterService;
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

        master.getCommandManager().getCommands().forEach(cloudCommand -> {
            colouredConsoleProvider.info(cloudCommand.getName() + " | " + cloudCommand.getDescription() + " | " + Arrays.toString(cloudCommand.getAliases()));
        });

        return false;
    }
    //</editor-fold>
}
