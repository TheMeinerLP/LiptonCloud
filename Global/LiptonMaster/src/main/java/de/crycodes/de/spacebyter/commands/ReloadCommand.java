package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

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
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        liptonMaster.getParallelLoader().disableAddons();
        liptonMaster.getServerGroupConfig().getServerMetas();
        liptonMaster.getMasterConfig().reload();
        liptonMaster.getServerManager().start();
        liptonMaster.getProxyFileConfig().load();
        liptonMaster.getParallelLoader().loadAddons();
        liptonMaster.getParallelLoader().enableAddons();
        liptonMaster.getColouredConsoleProvider().info("Cloud was Reloaded!");
        return false;
    }
    //</editor-fold>
}
