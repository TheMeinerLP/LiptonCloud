package de.crycodes.de.spacebyter.liptoncloud.console;

import de.crycodes.de.spacebyter.liptoncloud.console.enums.Color;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.*;

/**
 * Coded By CryCodes
 * Class: LoggerProvider
 * Date : 22.07.2020
 * Time : 01:04
 * Project: LiptonCloud
 */

public class LoggerProvider {

    private ConsoleReader consoleReader;

    private final DefaultLogger loggerHandler = new DefaultLogger();

    private final LinkedBlockingQueue<Runnable> out = new LinkedBlockingQueue<>();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" ) {{
        this.setTimeZone( TimeZone.getTimeZone( "Europe/Berlin" ) );
    }};

    private Thread sideThread;

    public LoggerProvider(final File location) throws IOException {

        this.consoleReader = new ConsoleReader(System.in, System.out);
        this.consoleReader.setExpandEvents(false);

        AnsiConsole.systemInstall();

        Handler fileHandler = new FileHandler(location.getCanonicalPath() + "/latest", Integer.MAX_VALUE, 8, true);
        fileHandler.setFormatter(new SimpleFormatter(simpleDateFormat));
        fileHandler.setLevel(Level.ALL);
        fileHandler.setEncoding(StandardCharsets.UTF_8.name());
        loggerHandler.addHandler(fileHandler);


        sideThread =  new Thread(){

            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        out.take().run();
                    } catch (final InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        sideThread.setPriority(Thread.MIN_PRIORITY);
        sideThread.setDaemon(true);
        sideThread.start();


    }

    public void info(String message) {
        printLine("INFO", message);
    }
    public void error(String message) {
        printLine("ERROR", message);
    }
    public void warning(String message) {
        printLine("WARNING", message);
    }
    public void debug(String message) {
        printLine("DEBUG", message);
    }

    private void printLine(String perfix, String message){

        out.add(new Runnable() {

            @Override
            public void run() {
                loggerHandler.log(Level.INFO, Color.stripColor(message));

                try {
                    consoleReader.println(Ansi.ansi().eraseLine(
                            Ansi.Erase.ALL).toString() + ConsoleReader.RESET_LINE + colorString("[§a" + simpleDateFormat.format(System.currentTimeMillis()) + "§r] " +
                            "[" + perfix + "] "
                            + message)
                            + Ansi.ansi().reset().toString());

                    consoleReader.drawLine();
                    consoleReader.flush();

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

        });

    }

    public String readLine() {
        try {
            return this.consoleReader.readLine();
        } catch (final IOException ex) {
            ex.printStackTrace();
            return "null";
        }
    }

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }


    public String colorString(String text) {

        for (Color consoleColour : Color.values()) {
            text = text.replace('§' + "" + consoleColour.getIndex(), consoleColour.getAnsiCode());
        }

        return text;
    }
}
