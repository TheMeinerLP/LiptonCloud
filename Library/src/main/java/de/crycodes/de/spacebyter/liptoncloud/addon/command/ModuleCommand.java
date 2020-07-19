package de.crycodes.de.spacebyter.liptoncloud.addon.command;

import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: ModuleCommand
 * Date : 19.07.2020
 * Time : 20:25
 * Project: LiptonCloud
 */

public abstract class ModuleCommand {

    private final String name;
    private final String description;
    private final String[] aliases;

    public ModuleCommand(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    protected abstract boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args);

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
