package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: StopCommand
 * Date : 24.06.2020
 * Time : 20:47
 * Project: LiptonCloud
 */

public class StopCommand extends CloudCommand {

    public StopCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        System.exit(0);
        return false;
    }
}
