package de.crycodes.addon.multiproxy.commands;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: UnLinkProxyCommand
 * Date : 27.06.2020
 * Time : 15:02
 * Project: LiptonCloud
 */

public class UnLinkProxyCommand extends CloudCommand {

    public UnLinkProxyCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        return false;
    }
}
