package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;

/**
 * Coded By ErazeYT
 * Class: AuthKeyFailEvent
 * Date : 20.07.2020
 * Time : 00:37
 * Project: LiptonCloud3
 */

public class AuthKeyFailEvent extends Event {

    private final String key;

    public AuthKeyFailEvent(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
