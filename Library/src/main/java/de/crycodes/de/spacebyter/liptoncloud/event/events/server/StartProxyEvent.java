package de.crycodes.de.spacebyter.liptoncloud.event.events.server;

import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

/**
 * Coded By CryCodes
 * Class: StartProxyEvent
 * Date : 07.07.2020
 * Time : 12:42
 * Project: LiptonCloud
 */

public class StartProxyEvent extends Event {

    private final ProxyMeta proxyMeta;

    public StartProxyEvent(ProxyMeta proxyMeta) {
        this.proxyMeta = proxyMeta;
    }

    @Override
    public void setCancelled(boolean cancelled) { }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
