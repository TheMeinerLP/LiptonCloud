package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.commands.InstallCommand;
import de.crycodes.de.spacebyter.commands.StopCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.time.Counter;
import de.crycodes.de.spacebyter.liptoncloud.utils.DeletUtils;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.network.WrapperMasterClient;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.screen.ScreenManager;
import de.crycodes.de.spacebyter.server.ServerFileManager;
import de.crycodes.de.spacebyter.server.ServerStartHandler;
import de.crycodes.de.spacebyter.server.TemplateManager;
import de.crycodes.de.spacebyter.versionsmanager.VersionsManager;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: LiptonWrapper
 * Date : 24.06.2020
 * Time : 10:29
 * Project: LiptonCloud
 */

public class LiptonWrapper {

    //<editor-fold desc="Objects">
    private boolean isrunning = false;

    private ColouredConsoleProvider colouredConsoleProvider;
    private CommandManager commandManager;
    private FileManager fileManager;
    private WrapperConfig wrapperConfig;
    private LiptonLibrary liptonLibrary;
    private PacketHandler packetHandler;
    private WrapperMasterClient wrapperMasterClient;
    private Scheduler scheduler;
    private EventManager eventManager;
    private VersionsManager versionsManager;
    private Counter counter;
    private TemplateManager templateManager;
    private ServerStartHandler serverStartHandler;
    private ServerFileManager serverFileManager;
    private ScreenManager screenManager;
    //</editor-fold>

    //<editor-fold desc="LiptonWrapper">
    public LiptonWrapper() {
        isrunning = true;

        counter = new Counter();
        counter.start();

        DeletUtils.deleteDirectory(new File("./liptonWrapper/server/dynamic/"));

        fileManager = new FileManager("./liptonWrapper","api", "resources", "server", "logs", "server/dynamic","server/static", "templates").create();
        wrapperConfig = new WrapperConfig();

        colouredConsoleProvider = new ColouredConsoleProvider(new File("./liptonWrapper/logs"));
        colouredConsoleProvider.setUsecolor(wrapperConfig.isColorUse());

        if (wrapperConfig.getWrapperID() != null){
            if (wrapperConfig.getWrapperID().equalsIgnoreCase( " ") || wrapperConfig.getWrapperID().equalsIgnoreCase("-")) {
                System.out.println("Wrapper-ID in Config.json is invalidate!");
                System.exit(ExitUtil.TERMINATED);
            }
        } else {
            System.out.println("Wrapper-ID in Config.json is invalidate!");
            System.exit(ExitUtil.TERMINATED);
        }

        screenManager = new ScreenManager();

        scheduler = new Scheduler();
        eventManager = new EventManager();

        packetHandler = new PacketHandler();

        liptonLibrary = new LiptonLibrary(scheduler, eventManager, colouredConsoleProvider, wrapperConfig.isColorUse());
        liptonLibrary.checkAPIFile(new File("./liptonWrapper/api/LiptopnBridge-1.0-SNAPSHOT.jar"));

        liptonLibrary.registerPacket(packetHandler);

        liptonLibrary.printAscii();

        wrapperMasterClient = new WrapperMasterClient(this.wrapperConfig.getHost(), this.wrapperConfig.getPort(), this).start();
        versionsManager = new VersionsManager(wrapperConfig, this);

        templateManager = new TemplateManager(this);
        serverFileManager = new ServerFileManager(this);
        serverStartHandler = new ServerStartHandler(this);

        commandManager = new CommandManager(colouredConsoleProvider);
        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?"}, this));
        commandManager.registerCommand(new InstallCommand("install", "Installs a Spigot Version", new String[]{"version", "changeVersion"}, this));
        commandManager.registerCommand(new StopCommand("stop", "Stops The CloudSystem", new String[]{"quit", "exit"}, this));

        addShutDownHook();

        counter.stop();
        counter.printResult("WrapperStartup", this.colouredConsoleProvider);

        commandManager.run();


    }
    //</editor-fold>

    //<editor-fold desc="addShutDownHook">
    public void addShutDownHook(){
        Runtime.getRuntime().addShutdownHook(new ShutDownHookWrapper(this));
    }
    //</editor-fold>

    //<editor-fold desc="getter - setter">
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

    public VersionsManager getVersionsManager() {
        return versionsManager;
    }

    public Counter getCounter() {
        return counter;
    }

    public boolean isIsrunning() {
        return isrunning;
    }

    public void setIsrunning(boolean isrunning) {
        this.isrunning = isrunning;
    }

    public TemplateManager getTemplateManager() {
        return templateManager;
    }

    public ServerStartHandler getServerStartHandler() {
        return serverStartHandler;
    }

    public ServerFileManager getServerFileManager() {
        return serverFileManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }
    //</editor-fold>
}
