package de.crycodes.de.spacebyter.liptoncloud.event.events.server;

import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By CryCodes
 * Class: StopServerEvent
 * Date : 07.07.2020
 * Time : 12:42
 * Project: LiptonCloud
 */

public class StopServerEvent extends Event {

    private final ServerMeta serverMeta;

    public StopServerEvent(ServerMeta serverMeta) {
        this.serverMeta = serverMeta;
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    public ServerMeta getServerMeta() {
        return serverMeta;
    }
}
