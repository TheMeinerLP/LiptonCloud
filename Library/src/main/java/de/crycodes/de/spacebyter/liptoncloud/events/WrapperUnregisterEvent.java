package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

/**
 * Coded By ErazeYT
 * Class: WrapperUnregisterEvent
 * Date : 20.07.2020
 * Time : 00:16
 * Project: LiptonCloud3
 */

public class WrapperUnregisterEvent extends Event {

    private final WrapperMeta wrapperMeta;

    public WrapperUnregisterEvent(WrapperMeta wrapperMeta) {
        this.wrapperMeta = wrapperMeta;
    }

    public WrapperMeta getWrapperMeta() {
        return wrapperMeta;
    }
}
