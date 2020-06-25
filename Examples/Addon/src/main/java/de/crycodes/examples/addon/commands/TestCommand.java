package de.crycodes.examples.addon.commands;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: TestCommand
 * Date : 25.06.2020
 * Time : 15:45
 * Project: LiptonCloud
 */

public class TestCommand extends CloudCommand {

    public TestCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        colouredConsoleProvider.info("TEST MESSAGE FROM ADDON");
        return false;
    }
}
