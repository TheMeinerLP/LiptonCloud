package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.util.Arrays;

public class HelpCommand extends CloudCommand {

    private final LiptonWrapper wrapper;

    //<editor-fold desc="HelpCommand">
    public HelpCommand(String name, String description, String[] aliases, LiptonWrapper wrapper) {
        super(name, description, aliases);
        this.wrapper = wrapper;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        colouredConsoleProvider.info("Help Command:");

        colouredConsoleProvider.info("install list");
        colouredConsoleProvider.info("install <id>");
        colouredConsoleProvider.info("stop");
        colouredConsoleProvider.info("help");

        return false;
    }
    //</editor-fold>
}
