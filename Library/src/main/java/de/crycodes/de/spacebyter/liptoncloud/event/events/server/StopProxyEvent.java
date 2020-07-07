package de.crycodes.de.spacebyter.liptoncloud.event.events.server;

import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

/**
 * Coded By CryCodes
 * Class: StopProxyEvent
 * Date : 07.07.2020
 * Time : 12:42
 * Project: LiptonCloud
 */

public class StopProxyEvent extends Event {

    private final ProxyMeta proxyMeta;

    public StopProxyEvent(ProxyMeta proxyMeta) {
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