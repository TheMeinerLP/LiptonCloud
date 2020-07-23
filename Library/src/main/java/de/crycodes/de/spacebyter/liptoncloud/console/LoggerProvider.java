package de.crycodes.de.spacebyter.liptoncloud.console;

import de.crycodes.de.spacebyter.liptoncloud.console.enums.Color;
import jline.console.ConsoleReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.*;
import java.util.logging.SimpleFormatter;

/**
 * Coded By CryCodes
 * Class: LoggerProvider
 * Date : 22.07.2020
 * Time : 01:04
 * Project: LiptonCloud
 */

public class LoggerProvider {

    private ConsoleReader consoleReader;
    private final CloudLogger logger;

    public LoggerProvider() {
        this.logger = new CloudLogger();
        try {
            System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n");
            Handler fileHandler = new FileHandler("logs/latest.%g.log", 0, 10, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            fileHandler.setEncoding(StandardCharsets.UTF_8.name());
            this.logger.addHandler(fileHandler);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setEncoding(StandardCharsets.UTF_8.name());
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.SEVERE);
            this.logger.addHandler(consoleHandler);

        } catch (IOException exception) {
            this.logger.log(Level.SEVERE,"Something is wrong when set encoding or add filehandler",exception);
        }
        try {
            this.consoleReader = new ConsoleReader(System.in, System.out);
        } catch (IOException e) {
            this.logger.log(Level.SEVERE,"Something is wrong when the console starts",e);
        }
        this.consoleReader.setExpandEvents(false);
    }

    public void info(String message) {
        printLine(Level.INFO, message);
    }
    public void error(String message) {
        printLine(Level.SEVERE, message);
    }
    public void warning(String message) {
        printLine(Level.WARNING, message);
    }
    public void debug(String message) {
        printLine(Level.FINE, message);
    }

    private void printLine(Level level, String message){
        this.logger.log(level,message);
    }

    public String readLine() {
        try {
            return this.consoleReader.readLine();
        } catch (IOException ex) {
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

    public CloudLogger getLogger() {
        return logger;
    }
}
