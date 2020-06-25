package de.crycodes.addon.signsystem.commands;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: SignReloadCommand
 * Date : 25.06.2020
 * Time : 17:25
 * Project: LiptonCloud
 */

public class SignReloadCommand extends CloudCommand {
    public SignReloadCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        colouredConsoleProvider.info("[SIGNSYSTEM] RELOADED!");
        return false;
    }
}
