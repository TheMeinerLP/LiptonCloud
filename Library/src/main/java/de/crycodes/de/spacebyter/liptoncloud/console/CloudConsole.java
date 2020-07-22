package de.crycodes.de.spacebyter.liptoncloud.console;

import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Coded By CryCodes
 * Class: CloudConsole
 * Date : 22.07.2020
 * Time : 01:03
 * Project: LiptonCloud
 */

public class CloudConsole extends Thread {

    private volatile boolean running = true;
    private final LoggerProvider logger;
    private final CommandManager commandManager;
    private final String buffer;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" ) {{
        this.setTimeZone( TimeZone.getTimeZone( "Europe/Berlin" ) );
    }};

    public CloudConsole(LoggerProvider logger, CommandManager commandManager, String buffer) {
        this.logger = logger;
        this.buffer = buffer;
        this.commandManager = commandManager;

        setDaemon(true);
        setPriority(Thread.MIN_PRIORITY);

        start();
    }

    @Override
    public void run() {
        String line;

        while (!isInterrupted()) {
            try {
                logger.getConsoleReader().setPrompt("");
                logger.getConsoleReader().resetPromptLine("", "", 0);

                while ((line = logger.getConsoleReader()
                        .readLine(logger.colorString(
                                "[§a" + simpleDateFormat.format(System.currentTimeMillis()) + "§r]" +
                                        " Lipton"
                                        + "@" +
                                        buffer +
                                        " ~/")))
                        != null &&

                        !line.trim().isEmpty() && running) {

                    logger.getConsoleReader().setPrompt("");

                    commandManager.execute(line, this);

                }
            } catch (IOException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void clearScreen(){
        try {
            this.logger.getConsoleReader().clearScreen();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public LoggerProvider getLogger() {
        return logger;
    }

}
