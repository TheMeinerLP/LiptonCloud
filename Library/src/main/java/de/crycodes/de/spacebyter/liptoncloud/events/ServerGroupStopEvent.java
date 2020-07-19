package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

/**
 * Coded By ErazeYT
 * Class: GroupStopEvent
 * Date : 19.07.2020
 * Time : 23:44
 * Project: LiptonCloud3
 */

public class ServerGroupStopEvent extends Event {

    private final ServerGroupMeta serverGroupMeta;

    public ServerGroupStopEvent(ServerGroupMeta serverGroupMeta) {
        this.serverGroupMeta = serverGroupMeta;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }
}
