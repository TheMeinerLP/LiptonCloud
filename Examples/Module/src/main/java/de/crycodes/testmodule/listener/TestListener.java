package de.crycodes.testmodule.listener;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventHandler;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventType;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.enums.EventTarget;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;
import de.crycodes.testmodule.events.TestEvent;

/**
 * Coded By CryCodes
 * Class: Testlistener
 * Date : 19.07.2020
 * Time : 21:33
 * Project: LiptonCloud
 */

public class TestListener implements Listener {

    @EventHandler @EventType(eventTarget = EventTarget.AUTH)
    public void handelEvent(TestEvent event){
        System.out.println(event.getText());
    }

}
