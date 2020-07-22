package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.LiptonMasterService;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

import java.util.Arrays;

/**
 * Coded By CryCodes
 * Class: HelpCommand
 * Date : 24.06.2020
 * Time : 10:49
 * Project: LiptonCloud
 */

public class HelpCommand extends CloudCommand {

    private final LiptonMaster master;

    //<editor-fold desc="HelpCommand">
    public HelpCommand(String name, String description, String[] aliases, LiptonMaster master) {
        super(name, description, aliases);
        this.master = master;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {

        master.getCloudConsole().getLogger().info("CloudCommands: ");

        master.getCommandManager().getCommands().forEach(cloudCommand -> {
            colouredConsoleProvider.getLogger().info(cloudCommand.getName() + " | " + cloudCommand.getDescription() + " | " + Arrays.toString(cloudCommand.getAliases()));
        });

        master.getCloudConsole().getLogger().info("ModuleCommands: ");

        if (master.getCommandManager().getModuleCommands().isEmpty()){
            colouredConsoleProvider.getLogger().info("No ModuleCommands found!");
            return false;
        }

        master.getCommandManager().getModuleCommands().forEach(moduleCommand -> {
            colouredConsoleProvider.getLogger().info(moduleCommand.getName() + " | " + moduleCommand.getDescription() + " | " + Arrays.toString(moduleCommand.getAliases()));
        });

        return false;
    }
    //</editor-fold>
}
