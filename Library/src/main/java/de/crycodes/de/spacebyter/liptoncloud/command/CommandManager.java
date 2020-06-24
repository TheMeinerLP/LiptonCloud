package de.crycodes.de.spacebyter.liptoncloud.command;

import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Coded By CryCodes
 * Class: CommandManager
 * Date : 24.06.2020
 * Time : 10:33
 * Project: LiptonCloud
 */

public class CommandManager {

    private List<CloudCommand> commands = new ArrayList<>();
    private final ColouredConsoleProvider colouredConsoleProvider;

    private Boolean isActive = true;
    private Scanner scanner;


    public CommandManager(ColouredConsoleProvider colouredConsoleProvider){
        this.colouredConsoleProvider = colouredConsoleProvider;
    }

    public void registerCommand(CloudCommand cloudCommand){
        this.commands.add(cloudCommand);
    }

    public void run() {
        this.setActive(true);
        scanner = new Scanner(System.in);
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
        CloudCommand command = getCommand(commandtext);

        List<String> args = new ArrayList<>();
        for (String argument : line.substring( commandtext.length() ).split(" ")){
            if (argument.equalsIgnoreCase("") || argument.equalsIgnoreCase(" ")){
                continue;
            }
            args.add(argument);
        }
        command.execute(colouredConsoleProvider, line, args.toArray( new String[args.size()]) );
    }

    public CloudCommand getCommand(String commandName){
        for (CloudCommand cloudCommand : commands){
            if (cloudCommand.getName().equalsIgnoreCase(commandName) || Arrays.asList(cloudCommand.getAliases()).contains(commandName)){
                return cloudCommand;
            }
        }
        return null;
    }

    public List<CloudCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<CloudCommand> commands) {
        this.commands = commands;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void restart() {
        this.setActive(true);
    }
}
