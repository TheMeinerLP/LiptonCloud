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
       // ColouredConsoleProvider.getGlobal().info(message);
    }

    @Override
    public void warning(String message) {
       // ColouredConsoleProvider.getGlobal().warning(message);
    }

    @Override
    public void error(String message) {
    }

    @Override
    public void debug(String message) {
       // ColouredConsoleProvider.getGlobal().debug(message);
    }

    public static MessageLogger getGlobalLogger(){
        return new MessageLogger();
    }
}
