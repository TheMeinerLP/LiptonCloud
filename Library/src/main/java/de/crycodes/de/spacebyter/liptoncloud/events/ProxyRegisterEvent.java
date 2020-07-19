package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;

/**
 * Coded By ErazeYT
 * Class: ProxyRegisterEvent
 * Date : 20.07.2020
 * Time : 00:19
 * Project: LiptonCloud3
 */

public class ProxyRegisterEvent extends Event {

    private final ProxyMeta proxyMeta;

    public ProxyRegisterEvent(ProxyMeta proxyMeta) {
        this.proxyMeta = proxyMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }
}
