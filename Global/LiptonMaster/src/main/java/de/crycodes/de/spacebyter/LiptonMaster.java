package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.*;
import de.crycodes.de.spacebyter.config.*;
import de.crycodes.de.spacebyter.liptoncloud.time.Counter;
import de.crycodes.de.spacebyter.networking.proxy.MasterProxyServer;
import de.crycodes.de.spacebyter.networking.server.MasterSpigotServer;
import de.crycodes.de.spacebyter.serverhelper.ProxyFileConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.manager.*;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.networking.MasterWrapperServer;

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

    private LiptonLibrary liptonLibrary;
    private Scheduler scheduler;
    private AddonParallelLoader parallelLoader;
    private ServerGroupConfig serverGroupConfig;

    private PacketHandler packetHandler;

    private ProxyGroupConfig proxyGroupConfig;
    private WrapperGroupConfig wrapperConfig;
    private MasterConfig masterConfig;
    private ProxyFileConfig proxyFileConfig;

    private CommandManager commandManager;
    private FileManager fileManager;
    private WrapperManager wrapperManager;
    private ServerGlobalManager serverGlobalManager;
    private PortManager portManager;
    private IDManager idManager;
    private EventManager eventManager;
    private ServerManager serverManager;
    private ProxyManager proxyManager;
    private Counter counter;

    private MasterWrapperServer masterWrapperServer;
    private MasterProxyServer masterProxyServer;
    private MasterSpigotServer masterSpigotServer;

    public LiptonMaster() {
        instance = this;

        counter = new Counter();
        counter.start();

        fileManager = new FileManager("./liptonMaster", "groups","groups/server/","groups/proxy/","groups/wrapper/", "local", "logs", "modules", "proxys", "webserver", "api", "resources").create();

        masterConfig = new MasterConfig();

        proxyFileConfig = new ProxyFileConfig();

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
        masterProxyServer = new MasterProxyServer(1784).start();
        masterSpigotServer = new MasterSpigotServer(7898).start();


        serverManager = new ServerManager(this, scheduler);
        serverManager.start();



        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?", "tftodo"}, this));
        commandManager.registerCommand(new CreateCommand("create", "Create Wrapper|Proxy|ServerGroups ", new String[]{"build", "make"}));
        commandManager.registerCommand(new ReloadCommand("reload", "Reload Cloud", new String[]{"restart", "reloadconfig"}, this));
        commandManager.registerCommand(new StopCommand("stop", "Stop the Cloud", new String[]{"exit"}));
        commandManager.registerCommand(new ServiceCommand("service", "Service Command of the Cloud", new String[]{"cloud"}));

        counter.stop();
        counter.printResult("MasterStartup" ,this.getColouredConsoleProvider());

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

    public ProxyFileConfig getProxyFileConfig() {
        return proxyFileConfig;
    }

    public MasterProxyServer getMasterProxyServer() {
        return masterProxyServer;
    }

    public MasterSpigotServer getMasterSpigotServer() {
        return masterSpigotServer;
    }
}
