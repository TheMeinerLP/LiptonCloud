package de.crycodes.de.spacebyter.network.logger;

/**
 * Coded By CryCodes
 * Class: MessageLogger
 * Date : 31.05.2020
 * Time : 13:38
 * Project: Networking-Framework
 */

public class MessageLogger implements MessageLoggerInterface {

    @Override
    public void info(String message) {
        //System.out.println("[INFO]> " + message);
    }

    @Override
    public void warning(String message) {
        //System.out.println("[WARNING]> " + message);
    }

    @Override
    public void error(String message) {
        //System.out.println("[ERROR]> " + message);
    }

    @Override
    public void debug(String message) {
        //System.out.println("[DEBUG]> " + message);
    }

    public static MessageLogger getGlobalLogger(){
        return new MessageLogger();
    }
}
