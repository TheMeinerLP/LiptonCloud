package de.crycodes.de.spacebyter.liptoncloud.console;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Coded By CryCodes
 * Class: LoggerHandler
 * Date : 22.07.2020
 * Time : 01:17
 * Project: LiptonCloud
 */

public class DefaultLogger extends Logger {

    public DefaultLogger() {
        super("Logger", null);
        setLevel(Level.ALL);
        setUseParentHandlers(false);
    }

}
