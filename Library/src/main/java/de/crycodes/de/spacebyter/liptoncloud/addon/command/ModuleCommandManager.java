package de.crycodes.de.spacebyter.liptoncloud.addon.command;

import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Coded By CryCodes
 * Class: ModuleCommandManager
 * Date : 19.07.2020
 * Time : 20:25
 * Project: LiptonCloud
 */

public final class ModuleCommandManager {

    private List<ModuleCommand> commands = new ArrayList<>();
    private final ColouredConsoleProvider colouredConsoleProvider;

    private Boolean isActive = true;
    private final Scanner scanner;


    public ModuleCommandManager(ColouredConsoleProvider colouredConsoleProvider, Scanner scanner){
        this.colouredConsoleProvider = colouredConsoleProvider;
        this.scanner = scanner;
    }

    public void registerCommand(ModuleCommand moduleCommand){
        this.commands.add(moduleCommand);
    }

    public void run() {
        this.setActive(true);
        while (scanner.hasNext()){
            if (isActive)
                execute(scanner.nextLine());
        }

    }
    public void stop(){
        this.setActive(false);
    }

    public void execute(String line){
        String commandtext = line.split(" ")[0];

        if ( getCommand(commandtext) == null){
            this.colouredConsoleProvider.error("Command Not Found!");
            return;
        }
        ModuleCommand command = getCommand(commandtext);

        List<String> args = new ArrayList<>();
        for (String argument : line.substring( commandtext.length() ).split(" ")){
            if (argument.equalsIgnoreCase("") || argument.equalsIgnoreCase(" ")){
                continue;
            }
            args.add(argument);
        }
        command.execute(colouredConsoleProvider, line, args.toArray( new String[args.size()]) );
    }

    public ModuleCommand getCommand(String commandName){
        for (ModuleCommand moduleCommand : commands){
            if (moduleCommand.getName().equalsIgnoreCase(commandName) || Arrays.asList(moduleCommand.getAliases()).contains(commandName)){
                return moduleCommand;
            }
        }
        return null;
    }

    public List<ModuleCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ModuleCommand> commands) {
        this.commands = commands;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
