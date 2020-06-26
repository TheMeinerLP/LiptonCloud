package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.*;
import de.crycodes.de.spacebyter.config.*;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.manager.*;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.MasterWrapperServer;

import java.io.File;
import java.util.LinkedList;

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
    private PortManager portManager;
    private IDManager idManager;
    private Scheduler scheduler;
    private EventManager eventManager;
    private AddonParallelLoader parallelLoader;
    private ServerManager serverManager;
    private ServerGroupConfig serverGroupConfig;
    private WrapperManager wrapperManager;
    private ProxyGroupConfig proxyGroupConfig;
    private WrapperGroupConfig wrapperConfig;
    private PacketHandler packetHandler;
    private ServerGlobalManager serverGlobalManager;
    private ProxyManager proxyManager;
    private MasterWrapperServer masterWrapperServer;

    public LiptonMaster() {
        instance = this;

        fileManager = new FileManager("./liptonMaster", "groups","groups/server/","groups/proxy/","groups/wrapper/", "local", "logs", "modules", "proxys", "webserver", "api", "resources").create();

        masterConfig = new MasterConfig();

        serverGroupConfig = new ServerGroupConfig();
        proxyGroupConfig = new ProxyGroupConfig();
        wrapperConfig = new WrapperGroupConfig();

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonMaster/logs"));
        colouredConsoleProvider.setUsecolor(this.masterConfig.isColorConsole());

        portManager = new PortManager(this);
        idManager = new IDManager();

        serverGlobalManager = new ServerGlobalManager();
        proxyManager = new ProxyManager();

        if (serverGroupConfig.getServerMetaByName("Lobby") == null)
            serverGroupConfig.create(new ServerGroupMeta("Lobby",
                    512,
                    128,
                    true,
                    false ,
                    10,
                    2));



        scheduler = new Scheduler();
        eventManager = new EventManager();

        wrapperManager = new WrapperManager(this);

        commandManager = new CommandManager(colouredConsoleProvider);

        parallelLoader = new AddonParallelLoader("./liptonMaster/modules");

        liptonLibrary = new LiptonLibrary(scheduler, eventManager, colouredConsoleProvider, parallelLoader, masterConfig.isColorConsole());

        packetHandler = new PacketHandler();
        liptonLibrary.registerPacket(packetHandler);

        liptonLibrary.printAscii();

        parallelLoader.loadAddons();
        parallelLoader.enableAddons();

        if (parallelLoader.isAddonEnabled("SignSystem"))
            colouredConsoleProvider.info("Using SignSystem for LiptonCloud!");

        masterWrapperServer = new MasterWrapperServer(this.masterConfig.getPort()).start();


        serverManager = new ServerManager(this, scheduler);
        serverManager.start();



        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?", "tftodo"}, this));
        commandManager.registerCommand(new CreateCommand("create", "Create Wrapper|Proxy|ServerGroups ", new String[]{"build", "make"}));
        commandManager.registerCommand(new ReloadCommand("reload", "Reload Cloud", new String[]{"restart", "reloadconfig"}, this));
        commandManager.registerCommand(new StopCommand("stop", "Stop the Cloud", new String[]{"exit"}));
        commandManager.registerCommand(new ServiceCommand("service", "Service Command of the Cloud", new String[]{"cloud"}));

        commandManager.run();
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

    public ServerGroupConfig getServerGroupConfig() {
        return serverGroupConfig;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public PortManager getPortManager() {
        return portManager;
    }

    public WrapperManager getWrapperManager() {
        return wrapperManager;
    }

    public IDManager getIdManager() {
        return idManager;
    }

    public ProxyGroupConfig getProxyGroupConfig() {
        return proxyGroupConfig;
    }

    public WrapperGroupConfig getWrapperConfig() {
        return wrapperConfig;
    }

    public ServerGlobalManager getServerGlobalManager() {
        return serverGlobalManager;
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    public ProxyManager getProxyManager() {
        return proxyManager;
    }

    public MasterWrapperServer getMasterWrapperServer() {
        return masterWrapperServer;
    }
}
