package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerStopEvent
 * Date : 19.07.2020
 * Time : 23:43
 * Project: LiptonCloud3
 */

public class ServerStopEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerStopEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
