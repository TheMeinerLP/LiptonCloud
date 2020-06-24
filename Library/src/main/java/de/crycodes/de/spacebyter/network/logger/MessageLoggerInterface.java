package de.crycodes.de.spacebyter.network.logger;

public interface MessageLoggerInterface {

    public void info(String message);
    public void warning(String message);
    public void error(String message);
    public void debug(String message);
}
