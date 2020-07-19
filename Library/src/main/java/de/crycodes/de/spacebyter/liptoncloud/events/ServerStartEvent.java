package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerStartEvent
 * Date : 20.07.2020
 * Time : 00:28
 * Project: LiptonCloud3
 */

public class ServerStartEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerStartEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
