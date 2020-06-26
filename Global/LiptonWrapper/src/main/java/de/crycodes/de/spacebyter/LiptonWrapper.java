package de.crycodes.de.spacebyter;

import com.google.gson.internal.$Gson$Preconditions;
import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.network.WrapperMasterClient;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;

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
    private PacketHandler packetHandler;
    private WrapperMasterClient wrapperMasterClient;
    private Scheduler scheduler;
    private EventManager eventManager;
    private AddonParallelLoader parallelLoader;

    public LiptonWrapper() {
        instance = this;

        fileManager = new FileManager("./liptonWrapper","api","modules", "resources", "server", "logs", "server/dynamic","server/static", "templates").create();
        wrapperConfig = new WrapperConfig();

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonWrapper/logs"));
        colouredConsoleProvider.setUsecolor(wrapperConfig.isColorUse());

        if (wrapperConfig.getWrapperID().equalsIgnoreCase( " ") || wrapperConfig.getWrapperID().equalsIgnoreCase("-"))
            System.exit(ExitUtil.TERMINATED);

        scheduler = new Scheduler();
        eventManager = new EventManager();

        packetHandler = new PacketHandler();

        parallelLoader = new AddonParallelLoader("./liptonWrapper/modules");
        parallelLoader.loadAddons();
        parallelLoader.enableAddons();

        liptonLibrary = new LiptonLibrary(scheduler, eventManager, colouredConsoleProvider, parallelLoader, wrapperConfig.isColorUse());

        liptonLibrary.registerPacket(packetHandler);

        liptonLibrary.printAscii();

        wrapperMasterClient = new WrapperMasterClient(this.wrapperConfig.getHost(), this.wrapperConfig.getPort()).start();



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

    public FileManager getFileManager() {
        return fileManager;
    }

    public WrapperConfig getWrapperConfig() {
        return wrapperConfig;
    }

    public LiptonLibrary getLiptonLibrary() {
        return liptonLibrary;
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    public WrapperMasterClient getWrapperMasterClient() {
        return wrapperMasterClient;
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
