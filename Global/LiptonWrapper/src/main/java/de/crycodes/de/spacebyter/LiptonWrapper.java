package de.crycodes.de.spacebyter;

import de.crycodes.de.spacebyter.commands.HelpCommand;
import de.crycodes.de.spacebyter.commands.InstallCommand;
import de.crycodes.de.spacebyter.commands.ScreenCommand;
import de.crycodes.de.spacebyter.commands.StopCommand;
import de.crycodes.de.spacebyter.config.FileManager;
import de.crycodes.de.spacebyter.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.console.LoggerProvider;
import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
import de.crycodes.de.spacebyter.liptoncloud.library.JarInjector;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.WrapperSetup;
import de.crycodes.de.spacebyter.liptoncloud.time.Counter;
import de.crycodes.de.spacebyter.liptoncloud.utils.AsciiPrinter;
import de.crycodes.de.spacebyter.liptoncloud.utils.DeletUtils;
import de.crycodes.de.spacebyter.liptoncloud.utils.PropertiesUtils;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;
import de.crycodes.de.spacebyter.manager.DeleteServerManager;
import de.crycodes.de.spacebyter.network.WrapperMasterClient;
import de.crycodes.de.spacebyter.network.packet.PacketHandler;
import de.crycodes.de.spacebyter.screen.ScreenManager;
import de.crycodes.de.spacebyter.manager.ServerFileManager;
import de.crycodes.de.spacebyter.screen.ScreenPrinter;
import de.crycodes.de.spacebyter.server.ServerStartHandler;
import de.crycodes.de.spacebyter.manager.TemplateManager;
import de.crycodes.de.spacebyter.manager.VersionsManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

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

    private CloudConsole colouredConsoleProvider;
    private LoggerProvider loggerProvider;

    private CommandManager commandManager;
    private FileManager fileManager;
    private WrapperConfig wrapperConfig;
    private LiptonLibrary liptonLibrary;
    private PacketHandler packetHandler;
    private WrapperMasterClient wrapperMasterClient;
    private Scheduler scheduler;
    private VersionsManager versionsManager;
    private Counter counter;
    private TemplateManager templateManager;
    private ServerStartHandler serverStartHandler;
    private ServerFileManager serverFileManager;
    private ScreenManager screenManager;
    private DeleteServerManager deleteServerManager;
    private JarInjector jarInjector;
    private EventManager eventManager;
    private ScreenPrinter screenPrinter;
    //</editor-fold>

    //<editor-fold desc="LiptonWrapper">
    public LiptonWrapper() throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InterruptedException {
        isrunning = true;

        counter = new Counter();
        counter.start();

        DeletUtils.deleteDirectory(new File("./liptonWrapper/server/dynamic/"));

        fileManager = new FileManager("./liptonWrapper","api", "resources", "server", "logs","librarys", "server/dynamic","server/static", "templates").create();
        wrapperConfig = new WrapperConfig(false);

        jarInjector = new JarInjector(new File("./liptonWrapper/librarys/"));

        loggerProvider = new LoggerProvider(new File("./liptonWrapper/logs"));

        commandManager = new CommandManager();

        colouredConsoleProvider = new CloudConsole(loggerProvider, commandManager, PropertiesUtils.USER_NAME);

        if (!wrapperConfig.isSetupDone()){
            new AsciiPrinter().Print(this.colouredConsoleProvider, false);

            WrapperSetup wrapperSetup = new WrapperSetup();
            wrapperSetup.start(colouredConsoleProvider);
            wrapperConfig.createFromSetup(wrapperSetup.getGroupName(),wrapperSetup.getColor(),wrapperSetup.getMasterhost());
            colouredConsoleProvider.getLogger().info("Wrapper will restart in 3 Seconds!");
            Thread.sleep(3000);
            System.exit(ExitState.STOPPED_SUCESS.getState());
        }



        versionsManager = new VersionsManager(wrapperConfig, this);

        if(wrapperConfig.getSpigotVersion().equals("-") || wrapperConfig.getSpigotVersion() == null) {
            colouredConsoleProvider.getLogger().error("No Version Defined in Config use (Cloud installed default Version: 1.8.8-SPIGOT)!");
            versionsManager.install(SpigotVersions.SPIGOT_1_8_8);
        }

        if (wrapperConfig.getWrapperID() != null){
            if (wrapperConfig.getWrapperID().equalsIgnoreCase( " ") || wrapperConfig.getWrapperID().equalsIgnoreCase("-")) {
                System.out.println("Wrapper-ID in Config.json is invalidate!");
                System.exit(ExitState.TERMINATED.getState());
            }
        } else {
            System.out.println("Wrapper-ID in Config.json is invalidate!");
            System.exit(ExitState.TERMINATED.getState());
        }



        screenManager = new ScreenManager();

        scheduler = new Scheduler();

        packetHandler = new PacketHandler();

        liptonLibrary = new LiptonLibrary(scheduler, wrapperConfig.isColorUse());
        liptonLibrary.checkAPIFile(new File("./liptonWrapper/api/LiptonBridge-1.0-SNAPSHOT.jar"));

        liptonLibrary.registerPacket(packetHandler, colouredConsoleProvider);

        new AsciiPrinter().Print(this.colouredConsoleProvider, wrapperConfig.isColorUse());

        wrapperMasterClient = new WrapperMasterClient(this.wrapperConfig.getHost(), this.wrapperConfig.getPort(), this).start();


        templateManager = new TemplateManager(this);
        serverFileManager = new ServerFileManager(this);
        serverStartHandler = new ServerStartHandler(this);

        deleteServerManager = new DeleteServerManager(this);

        eventManager = new EventManager();

        screenPrinter = new ScreenPrinter(this.getColouredConsoleProvider(), this);

        commandManager.registerCommand(new HelpCommand("help", "Shows all CloudCommands", new String[]{"?"}, this));
        commandManager.registerCommand(new InstallCommand("install", "Installs a Spigot Version", new String[]{"version", "changeVersion"}, this));
        commandManager.registerCommand(new StopCommand("stop", "Stops The CloudSystem", new String[]{"quit", "exit"}, this));
        commandManager.registerCommand(new ScreenCommand("screen", "Screen Command to see Console Output off the servers", this, screenPrinter, "servers"));

        addShutDownHook();

        counter.stop();
        counter.printResult("WrapperStartup", this.colouredConsoleProvider);


    }
    //</editor-fold>

    //<editor-fold desc="addShutDownHook">
    public void addShutDownHook(){
        Runtime.getRuntime().addShutdownHook(new ShutDownHookWrapper(this));
    }
    //</editor-fold>

    //<editor-fold desc="getter - setter">

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

    public DeleteServerManager getDeleteServerManager() {
        return deleteServerManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ScreenPrinter getScreenPrinter() {
        return screenPrinter;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public CloudConsole getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }

    public JarInjector getJarInjector() {
        return jarInjector;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public LoggerProvider getLoggerProvider() {
        return loggerProvider;
    }
    //</editor-fold>
}
