package de.crycodes.eventexample.event;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;

/**
 * Coded By CryCodes
 * Class: ExampleEvent
 * Date : 20.07.2020
 * Time : 12:04
 * Project: LiptonCloud
 */

public class ExampleEvent extends Event {

    private final Integer number;

    public ExampleEvent(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}
