package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerCopyEvent
 * Date : 19.07.2020
 * Time : 23:40
 * Project: LiptonCloud3
 */

public class ServerCopyEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerCopyEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
