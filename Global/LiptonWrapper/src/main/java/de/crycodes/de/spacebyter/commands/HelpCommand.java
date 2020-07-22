package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

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
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {

        colouredConsoleProvider.getLogger().info("CloudCommands: ");

        wrapper.getCommandManager().getCommands().forEach(cloudCommand -> {
            colouredConsoleProvider.getLogger().info(cloudCommand.getName() + " | " + cloudCommand.getDescription() + " | " + Arrays.toString(cloudCommand.getAliases()));
        });



        return false;
    }
    //</editor-fold>
}
