package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.util.Arrays;

public class HelpCommand extends CloudCommand {

    private final LiptonWrapper wrapper;

    public HelpCommand(String name, String description, String[] aliases, LiptonWrapper wrapper) {
        super(name, description, aliases);
        this.wrapper = wrapper;
    }

    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        colouredConsoleProvider.info("Help Command:");

        wrapper.getCommandManager().getCommands().forEach(commands -> {
            colouredConsoleProvider.info(commands.getName() + " | " + commands.getDescription() + " | " + Arrays.asList(commands.getAliases()).toString());
        });

        return false;
    }
}
