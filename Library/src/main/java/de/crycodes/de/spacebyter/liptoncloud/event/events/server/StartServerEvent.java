package de.crycodes.de.spacebyter.liptoncloud.event.events.server;

import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

/**
 * Coded By CryCodes
 * Class: StartServerEvent
 * Date : 07.07.2020
 * Time : 12:42
 * Project: LiptonCloud
 */

public class StartServerEvent extends Event {

    private final ServerMeta serverMeta;

    public StartServerEvent(ServerMeta serverMeta) {
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
