package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

/**
 * Coded By ErazeYT
 * Class: ServerGroupCreateEvent
 * Date : 19.07.2020
 * Time : 23:16
 * Project: LiptonCloud3
 */

public class ServerGroupCreateEvent extends Event {

    private final ServerGroupMeta groupMeta;

    public ServerGroupCreateEvent(ServerGroupMeta groupMeta) {
        this.groupMeta = groupMeta;
    }

    public ServerGroupMeta getGroupMeta() {
        return groupMeta;
    }
}
