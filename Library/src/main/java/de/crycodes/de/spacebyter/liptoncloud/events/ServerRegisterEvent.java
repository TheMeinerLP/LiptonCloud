package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By ErazeYT
 * Class: ServerRegisterEvent
 * Date : 20.07.2020
 * Time : 00:20
 * Project: LiptonCloud3
 */

public class ServerRegisterEvent extends Event {

    private final ServerMeta serverMeta;

    public ServerRegisterEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
