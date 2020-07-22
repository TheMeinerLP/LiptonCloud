package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

/**
 * Coded By CryCodes
 * Class: StopCommand
 * Date : 24.06.2020
 * Time : 20:47
 * Project: LiptonCloud
 */

public class StopCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="StopCommand">
    public StopCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        System.exit(0);
        return false;
    }
    //</editor-fold>
}
