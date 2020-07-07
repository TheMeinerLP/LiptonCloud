package de.crycodes.de.spacebyter.liptoncloud.event.events;

import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;

/**
 * Coded By CryCodes
 * Class: CloudStartedEvent
 * Date : 07.07.2020
 * Time : 12:35
 * Project: LiptonCloud
 */

public class CloudStartedEvent extends Event {

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
