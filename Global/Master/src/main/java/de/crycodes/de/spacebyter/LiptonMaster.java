package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.MasterConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;

import java.io.File;

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

    private Scheduler scheduler;
    private EventManager eventManager;
    private AddonParallelLoader parallelLoader;

    public LiptonMaster() {
        instance = this;



        fileManager = new FileManager("./liptonMaster", "groups", "local", "logs", "modules", "proxys", "webserver", "api", "resources").create();

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonMaster/logs"));


        masterConfig = new MasterConfig();

        scheduler = new Scheduler();
        eventManager = new EventManager();

        parallelLoader = new AddonParallelLoader("./liptonMaster/modules");
        parallelLoader.loadAddons();
        parallelLoader.enableAddons();

        liptonLibrary = new LiptonLibrary(scheduler, eventManager, colouredConsoleProvider, parallelLoader, masterConfig.isColorConsole());

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

    public FileManager getFileManager() {
        return fileManager;
    }

    public MasterConfig getMasterConfig() {
        return masterConfig;
    }

    public LiptonLibrary getLiptonLibrary() {
        return liptonLibrary;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public AddonParallelLoader getParallelLoader() {
        return parallelLoader;
    }
}
