package de.crycodes.de.spacebyter.liptoncloud.command;


import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: Command
 * Date : 24.06.2020
 * Time : 10:30
 * Project: LiptonCloud
 */

public abstract class CloudCommand {

    private final String name;
    private final String description;
    private final String[] aliases;

    public CloudCommand(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    protected abstract boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}
