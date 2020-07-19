package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

/**
 * Coded By ErazeYT
 * Class: WrapperCreateEvent
 * Date : 19.07.2020
 * Time : 23:21
 * Project: LiptonCloud3
 */

public class WrapperCreateEvent extends Event {

    private final WrapperMeta wrapperMeta;

    public WrapperCreateEvent(WrapperMeta wrapperMeta) {
        this.wrapperMeta = wrapperMeta;
    }

    public WrapperMeta getWrapperMeta() {
        return wrapperMeta;
    }
}
