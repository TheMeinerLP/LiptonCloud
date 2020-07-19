package de.crycodes.testmodule.commands;

import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: TestCommand
 * Date : 19.07.2020
 * Time : 21:32
 * Project: LiptonCloud
 */

public class TestCommand extends ModuleCommand {

    public TestCommand(String name, String description, String... aliases) {
        super(name, description, aliases);
    }

    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        System.out.println("TEST");
        return false;
    }
}
