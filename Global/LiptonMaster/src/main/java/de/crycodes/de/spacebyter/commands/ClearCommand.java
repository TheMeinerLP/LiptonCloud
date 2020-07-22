package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

import java.io.IOException;

/**
 * Coded By CryCodes
 * Class: ClearCommand
 * Date : 19.07.2020
 * Time : 18:56
 * Project: LiptonCloud
 */

public class ClearCommand extends CloudCommand {

    public ClearCommand(String name, String description, String... aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {

        try {
            colouredConsoleProvider.getLogger().getConsoleReader().clearScreen();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return false;
    }


}
