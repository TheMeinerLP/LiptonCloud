package de.crycodes.de.spacebyter.liptoncloud.command;

import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

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

public final class CommandManager {

    private List<CloudCommand> commands = new ArrayList<>();
    private List<ModuleCommand> moduleCommands = new ArrayList<>();
    private Boolean isActive = true;

    public CommandManager(){ }

    public void registerCommand(CloudCommand cloudCommand){
        if (this.commands.contains(cloudCommand)) return;
        this.commands.add(cloudCommand);
    }
    public void registerModuleCommand(ModuleCommand moduleCommand){
        if (this.moduleCommands.contains(moduleCommand)) return;
        this.moduleCommands.add(moduleCommand);
    }

    public void stop(){
        this.setActive(false);
    }

    public boolean execute(String line, CloudConsole cloudConsole){
        String commandText = line.split(" ")[0];

        String[] split = line.substring(commandText.length()).split(" ");
        if ( getCommand(commandText) != null){

            CloudCommand command = getCommand(commandText);

            List<String> args = new ArrayList<>();
            for (String argument : split){
                if (argument.equalsIgnoreCase("") || argument.equalsIgnoreCase(" ")){
                    continue;
                }
                args.add(argument);
            }
            assert command != null;
            command.execute(cloudConsole, line, args.toArray(new String[0]) );
            return true;
        } else if ( getModuleCommand(commandText) != null){

            ModuleCommand moduleCommand = getModuleCommand(commandText);

            List<String> args = new ArrayList<>();
            for (String argument : split){
                if (argument.equalsIgnoreCase("") || argument.equalsIgnoreCase(" ")){
                    continue;
                }
                args.add(argument);
            }
            assert moduleCommand != null;
            moduleCommand.execute(cloudConsole, line, args.toArray(new String[0]) );
            return true;
        } else {

            cloudConsole.getLogger().error("Command Not Found!");
            return false;

        }


    }

    public CloudCommand getCommand(String commandName){
        for (CloudCommand cloudCommand : commands){
            if (cloudCommand.getName().equalsIgnoreCase(commandName) || Arrays.asList(cloudCommand.getAliases()).contains(commandName)){
                return cloudCommand;
            }
        }
        return null;
    }
    public ModuleCommand getModuleCommand(String commandName){
        for (ModuleCommand cloudCommand : moduleCommands){
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

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void restart() {
        this.setActive(true);
    }

    public List<ModuleCommand> getModuleCommands() {
        return moduleCommands;
    }
}
