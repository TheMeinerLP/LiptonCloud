package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

/**
 * Coded By ErazeYT
 * Class: ProxyUnregisterEvent
 * Date : 20.07.2020
 * Time : 00:19
 * Project: LiptonCloud3
 */

public class ProxyUnregisterEvent extends Event {

    private final ProxyMeta proxyMeta;

    public ProxyUnregisterEvent(ProxyMeta proxyMeta) {
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
