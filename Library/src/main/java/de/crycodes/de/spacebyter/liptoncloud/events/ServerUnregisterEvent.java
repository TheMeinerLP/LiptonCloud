package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerUnregisterEvent
 * Date : 20.07.2020
 * Time : 00:20
 * Project: LiptonCloud3
 */

public class ServerUnregisterEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerUnregisterEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
