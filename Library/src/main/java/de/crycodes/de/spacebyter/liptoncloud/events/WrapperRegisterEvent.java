package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

/**
 * Coded By ErazeYT
 * Class: WrapperRegisterEvent
 * Date : 20.07.2020
 * Time : 00:14
 * Project: LiptonCloud3
 */

public class WrapperRegisterEvent extends Event {

    private final WrapperMeta wrapperMeta;

    public WrapperRegisterEvent(WrapperMeta wrapperMeta) {
        this.wrapperMeta = wrapperMeta;
    }

    public WrapperMeta getWrapperMeta() {
        return wrapperMeta;
    }
}
