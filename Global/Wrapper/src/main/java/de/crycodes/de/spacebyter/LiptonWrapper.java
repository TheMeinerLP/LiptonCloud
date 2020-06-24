package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: LiptonWrapper
 * Date : 24.06.2020
 * Time : 10:29
 * Project: LiptonCloud
 */

public class LiptonWrapper {

    private static LiptonWrapper instance;

    private ColouredConsoleProvider colouredConsoleProvider;
    private CommandManager commandManager;
    private FileManager fileManager;
    private WrapperConfig wrapperConfig;
    private LiptonLibrary liptonLibrary;

    public LiptonWrapper() {
        instance = this;

        liptonLibrary = new LiptonLibrary();

        colouredConsoleProvider = new ColouredConsoleProvider();

        fileManager = new FileManager("./liptonWrapper","api", "resources", "server","server/dynamic","server/static", "templates").create();
        wrapperConfig = new WrapperConfig();


        commandManager = new CommandManager(colouredConsoleProvider);
        commandManager.registerComman(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?"}, this));
        commandManager.start();
    }

    public static LiptonWrapper getInstance() {
        return instance;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
