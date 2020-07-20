package de.crycodes.eventexample;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.EventManager;
import de.crycodes.eventexample.event.ExampleEvent;
import de.crycodes.eventexample.listener.ExampleListener;

/**
 * Coded By CryCodes
 * Class: StartUpClass
 * Date : 20.07.2020
 * Time : 12:01
 * Project: LiptonCloud
 */

public class StartUpClass {

    private EventManager eventManager; //THIS IS NOR REQUIRED IF UR USING MODULE SYSTEM!

    public StartUpClass() {

        eventManager = new EventManager();

        eventManager.unregisterListener(new ExampleListener());

        //EVENTS DONT NEED TO BE REGISTERED
        eventManager.callEvent(new ExampleEvent(5)); //CALL EVENT



    }
}
