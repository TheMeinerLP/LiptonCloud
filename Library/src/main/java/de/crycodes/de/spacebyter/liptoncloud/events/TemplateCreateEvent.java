package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

/**
 * Coded By ErazeYT
 * Class: TemplateCreateEvent
 * Date : 19.07.2020
 * Time : 23:58
 * Project: LiptonCloud3
 */

public class TemplateCreateEvent extends Event {

    private final ServerGroupMeta serverGroupMeta;

    public TemplateCreateEvent(ServerGroupMeta serverGroupMeta) {
        this.serverGroupMeta = serverGroupMeta;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }
}
