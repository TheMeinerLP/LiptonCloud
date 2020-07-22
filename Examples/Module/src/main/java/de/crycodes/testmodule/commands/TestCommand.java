package de.crycodes.testmodule.commands;

import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

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

    public boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        colouredConsoleProvider.getLogger().info("TEST");
        return false;
    }
}
