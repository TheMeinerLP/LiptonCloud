package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;

import java.io.File;

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

    private Scheduler scheduler;
    private EventManager eventManager;
    private AddonParallelLoader parallelLoader;

    public LiptonWrapper() {
        instance = this;

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonWrapper/logs"));

        fileManager = new FileManager("./liptonWrapper","api","modules", "resources", "server", "logs", "server/dynamic","server/static", "templates").create();
        wrapperConfig = new WrapperConfig();

        scheduler = new Scheduler();
        eventManager = new EventManager();

        parallelLoader = new AddonParallelLoader("./liptonWrapper/modules");
        parallelLoader.loadAddons();
        parallelLoader.enableAddons();

        liptonLibrary = new LiptonLibrary(scheduler, eventManager, colouredConsoleProvider, parallelLoader, wrapperConfig.isColorUse());

        commandManager = new CommandManager(colouredConsoleProvider);
        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?"}, this));
        commandManager.run();
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
