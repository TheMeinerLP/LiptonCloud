package de.crycodes.testmodule.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;

/**
 * Coded By CryCodes
 * Class: TestEvent
 * Date : 19.07.2020
 * Time : 21:33
 * Project: LiptonCloud
 */

public class TestEvent extends Event {

    private final String text;

    public TestEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
