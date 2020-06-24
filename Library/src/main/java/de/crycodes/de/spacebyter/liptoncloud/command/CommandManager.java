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

public class CommandManager extends Thread {

    private List<CloudCommand> commands = new ArrayList<>();
    private final ColouredConsoleProvider colouredConsoleProvider;

    public CommandManager(ColouredConsoleProvider colouredConsoleProvider){
        this.colouredConsoleProvider = colouredConsoleProvider;
    }

    public void registerComman(CloudCommand cloudCommand){
        this.commands.add(cloudCommand);
    }

    @Override
    public synchronized void start() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            execute(scanner.nextLine());
        }

    }

    public void execute(String line){
        if (!(line).contains(" ")){
            if (getCommand(line) != null)
                getCommand(line).execute(this.colouredConsoleProvider, line, new String[]{""});
            return;
        }
        String[] command = line.split(" ");
        String commandname = command[0];
        List<String> argslist = new ArrayList<>();
        for (String args : command){
            if (!args.equalsIgnoreCase(commandname)){
                argslist.add(args);
            }
        }
        getCommand(commandname).execute(this.colouredConsoleProvider, commandname,  argslist.toArray( new String[argslist.size()]));

    }

    public CloudCommand getCommand(String commandName){
        for (CloudCommand cloudCommand : commands){
            if (cloudCommand.getName().equalsIgnoreCase(commandName) || Arrays.asList(cloudCommand.getAliases()).contains(commandName)){
                return cloudCommand;
            } else {
                System.out.println("Command not found");
            }
        }
        return null;
    }

    public List<CloudCommand> getCommands() {
        return commands;
    }
}
