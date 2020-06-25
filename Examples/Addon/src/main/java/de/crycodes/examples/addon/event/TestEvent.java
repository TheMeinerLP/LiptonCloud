package de.crycodes.examples.addon.event;

import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;

/**
 * Coded By CryCodes
 * Class: TestEvent
 * Date : 25.06.2020
 * Time : 15:47
 * Project: LiptonCloud
 */

public class TestEvent extends Event {

    private ColouredConsoleProvider colouredConsoleProvider;

    public TestEvent(ColouredConsoleProvider colouredConsoleProvider) {
        this.colouredConsoleProvider = colouredConsoleProvider;
    }

    public void setCancelled(boolean cancelled) {

    }

    public boolean isCancelled() {
        return false;
    }

    public ColouredConsoleProvider getColouredConsoleProvider() {
        return colouredConsoleProvider;
    }
}
