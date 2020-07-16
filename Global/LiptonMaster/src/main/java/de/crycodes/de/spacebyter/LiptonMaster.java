package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.*;
import de.crycodes.de.spacebyter.config.*;
import de.crycodes.de.spacebyter.liptoncloud.time.Counter;
import de.crycodes.de.spacebyter.networking.proxy.MasterProxyServer;
import de.crycodes.de.spacebyter.networking.server.MasterSpigotServer;
import de.crycodes.de.spacebyter.proxy.BungeeCordManager;
import de.crycodes.de.spacebyter.serverhelper.ConfigHandler;
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

    //<editor-fold desc="Objects">
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
    private ConfigHandler proxyConfigHandler;
    private CloudAdminConfig adminConfig;
    private CloudMaintenanceConfig maintenanceConfig;

    private CommandManager commandManager;
    private FileManager fileManager;
    private WrapperManager wrapperManager;
    private PortManager portManager;
    private IDManager idManager;
    private EventManager eventManager;
    private ServerManager serverManager;
    private ProxyManager proxyManager;
    private Counter counter;

    private MasterWrapperServer masterWrapperServer;
    private MasterProxyServer masterProxyServer;
    private MasterSpigotServer masterSpigotServer;


    private BungeeCordManager bungeeCordManager;
    //</editor-fold>

    //<editor-fold desc="LiptonMaster">
    public LiptonMaster() {

        counter = new Counter();
        counter.start();

        fileManager = new FileManager("./liptonMaster", "groups","groups/server/","groups/proxy/","groups/wrapper/", "local", "logs", "modules", "proxys", "webserver", "api", "resources").create();

        masterConfig = new MasterConfig();

        proxyFileConfig = new ProxyFileConfig();

        adminConfig = new CloudAdminConfig(this);
        maintenanceConfig = new CloudMaintenanceConfig(this);

        serverGroupConfig = new ServerGroupConfig();
        proxyGroupConfig = new ProxyGroupConfig();
        wrapperConfig = new WrapperGroupConfig();

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonMaster/logs"));
        colouredConsoleProvider.setUsecolor(this.masterConfig.isColorConsole());

        portManager = new PortManager(this);
        idManager = new IDManager();

        proxyManager = new ProxyManager(this);

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
        liptonLibrary.checkAPIFile(new File("./liptonMaster/api/LiptopnBridge-1.0-SNAPSHOT.jar"));

        packetHandler = new PacketHandler();
        liptonLibrary.registerPacket(packetHandler);

        liptonLibrary.printAscii();

        proxyConfigHandler = new ConfigHandler(this,scheduler).startUpdateThread();

        parallelLoader.loadAddons();
        parallelLoader.enableAddons();

        if (parallelLoader.isAddonEnabled("SignSystem"))
            colouredConsoleProvider.info("Using SignSystem for LiptonCloud!");

        masterWrapperServer = new MasterWrapperServer(this.masterConfig.getPort(), this).start();
        masterProxyServer = new MasterProxyServer(1784, this).start();
        masterSpigotServer = new MasterSpigotServer(7898, this).start();


        serverManager = new ServerManager(this, serverGroupConfig);
        serverManager.start();

        bungeeCordManager = new BungeeCordManager(this);

        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?", "tftodo"}, this));
        commandManager.registerCommand(new CreateCommand("create", "Create Wrapper|Proxy|ServerGroups ", new String[]{"build", "make"}, this));
        commandManager.registerCommand(new ReloadCommand("reload", "Reload Cloud", new String[]{"restart", "reloadconfig"}, this));
        commandManager.registerCommand(new StopCommand("stop", "Stop the Cloud", new String[]{"exit"}, this));
        commandManager.registerCommand(new ServiceCommand("service", "Service Command of the Cloud", new String[]{"cloud"}, this));
        commandManager.registerCommand(new ExecuteCommand("execute", "Execute Command on Server", new String[]{"send"}, this));
        commandManager.registerCommand(new PermsCommand("perms", "Simple Command to manage CloudAdmins", new String[]{"cloudadmin", "user", "admin"}, this));
        commandManager.registerCommand(new MaintenanceCommand("maintenance", "Simple Maintenance Command for the Cloud", new String[]{}, this));
        commandManager.registerCommand(new CopyServerCommand("copy", "Simple Command to Copy Servers to Templates",  new String[]{}, this));

        counter.stop();
        counter.printResult("MasterStartup" ,this.getColouredConsoleProvider());

        commandManager.run();
    }
    //</editor-fold>

    //<editor-fold desc="Getter - Setter">
    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public void setColouredConsoleProvider(ColouredConsoleProvider colouredConsoleProvider) {
        this.colouredConsoleProvider = colouredConsoleProvider;
    }

    public CommandManager getCommandManager() {
        return commandManager;
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

    public CloudAdminConfig getAdminConfig() {
        return adminConfig;
    }

    public CloudMaintenanceConfig getMaintenanceConfig() {
        return maintenanceConfig;
    }

    public AddonParallelLoader getParallelLoader() {
        return parallelLoader;
    }
    //</editor-fold>
}
