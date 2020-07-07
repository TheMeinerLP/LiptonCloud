package de.crycodes.de.spacebyter.liptoncloud.event;
/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import de.crycodes.de.spacebyter.liptoncloud.event.enums.EventTargetType;
import de.crycodes.de.spacebyter.liptoncloud.event.events.CloudStartedEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StartProxyEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StartServerEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StopProxyEvent;
import de.crycodes.de.spacebyter.liptoncloud.event.events.server.StopServerEvent;

public abstract class Listener {

    public Listener(String name) {
        this.name = name;

    }

    private String name;

    public abstract void handel(CloudStartedEvent event);

    public abstract void handel(StartServerEvent event);
    public abstract void handel(StopServerEvent event);
    public abstract void handel(StartProxyEvent event);
    public abstract void handel(StopProxyEvent event);

    public String getName() {
        return name;
    }

}


