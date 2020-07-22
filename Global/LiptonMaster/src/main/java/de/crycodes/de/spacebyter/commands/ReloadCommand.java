package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

/**
 * Coded By CryCodes
 * Class: ReloadCommand
 * Date : 24.06.2020
 * Time : 20:12
 * Project: LiptonCloud
 */

public class ReloadCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="ReloadCommand">
    public ReloadCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        liptonMaster.getModuleService().stopModules();
        liptonMaster.getServerGroupConfig().getServerMetas();
        liptonMaster.getCommandManager().getModuleCommands().clear();
        liptonMaster.getMasterConfig().reload();
        liptonMaster.getProxyFileConfig().load();
        liptonMaster.getModuleService().loadModules(colouredConsoleProvider);
        liptonMaster.getModuleService().startModules();
        colouredConsoleProvider.getLogger().info("Cloud was Reloaded!");
        return false;
    }
    //</editor-fold>
}
