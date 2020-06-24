package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.MasterConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: LiptonMaster
 * Date : 24.06.2020
 * Time : 10:27
 * Project: LiptonCloud
 */

public class LiptonMaster {

    private static LiptonMaster instance;

    private ColouredConsoleProvider colouredConsoleProvider;
    private CommandManager commandManager;
    private FileManager fileManager;
    private MasterConfig masterConfig;
    private LiptonLibrary liptonLibrary;

    public LiptonMaster() {
        instance = this;

        liptonLibrary = new LiptonLibrary();

        colouredConsoleProvider = new ColouredConsoleProvider();

        fileManager = new FileManager("./liptonMaster", "groups", "local", "modules", "proxys", "webserver", "api", "resources").create();
        masterConfig = new MasterConfig();


        commandManager = new CommandManager(colouredConsoleProvider);
        commandManager.registerComman(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?", "tftodo"}, this));
        commandManager.start();
    }

    public static LiptonMaster getInstance() {
        return instance;
    }

    public static void setInstance(LiptonMaster instance) {
        LiptonMaster.instance = instance;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public void setColouredConsoleProvider(ColouredConsoleProvider colouredConsoleProvider) {
        this.colouredConsoleProvider = colouredConsoleProvider;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
