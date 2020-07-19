package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

/**
 * Coded By ErazeYT
 * Class: ProxyStopEvent
 * Date : 19.07.2020
 * Time : 23:46
 * Project: LiptonCloud3
 */

public class ProxyStopEvent extends Event {

    private final ProxyMeta proxyMeta;

    public ProxyStopEvent(ProxyMeta proxyMeta) {
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
