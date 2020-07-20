package de.crycodes.testmodule.listener;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventHandler;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventType;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.enums.EventTarget;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerAutoStartEvent;

/**
 * Coded By CryCodes
 * Class: ServerAutoStartListener
 * Date : 20.07.2020
 * Time : 11:45
 * Project: LiptonCloud
 */

public class ServerAutoStartListener implements Listener {

    @EventHandler @EventType(eventTarget = EventTarget.AUTOSTART)
    public void handelEvent(ServerAutoStartEvent event){
       // System.out.println(event.getServerMeta().toString());
    }


}
