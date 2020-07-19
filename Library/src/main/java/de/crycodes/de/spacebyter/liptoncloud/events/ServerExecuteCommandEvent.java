package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;

/**
 * Coded By ErazeYT
 * Class: ServerExecuteCommandEvent
 * Date : 19.07.2020
 * Time : 23:31
 * Project: LiptonCloud3
 */

public class ServerExecuteCommandEvent extends Event {

    private final String server;
    private final String command;

    public ServerExecuteCommandEvent(String server, String command) {
        this.server = server;
        this.command = command;
    }

    public String getServer() {
        return server;
    }

    public String getCommand() {
        return command;
    }
}
