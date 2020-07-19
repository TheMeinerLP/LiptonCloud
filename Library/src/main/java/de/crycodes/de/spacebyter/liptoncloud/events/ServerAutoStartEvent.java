package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerAutoStartEvent
 * Date : 20.07.2020
 * Time : 00:30
 * Project: LiptonCloud3
 */

public class ServerAutoStartEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerAutoStartEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
