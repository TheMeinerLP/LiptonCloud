package de.crycodes.eventexample.listener;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventHandler;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventType;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.enums.EventTarget;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;
import de.crycodes.eventexample.event.ExampleEvent;

/**
 * Coded By CryCodes
 * Class: ExampleListener
 * Date : 20.07.2020
 * Time : 12:07
 * Project: LiptonCloud
 */

public class ExampleListener implements Listener {

    @EventHandler @EventType(eventTarget = EventTarget.OTHER)
    public void handelEvent(ExampleEvent exampleEvent){
        System.out.println("NUMBER OF EVENT IS: " + exampleEvent.getNumber());
    }

}
