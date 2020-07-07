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
import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;

import java.util.ArrayList;
import java.util.List;

public final class EventManager {

    private final List<Listener> listeners = new ArrayList<>();
    private final List<Event> events = new ArrayList<>();

    public void registerListener(final Listener listener) {
        listeners.add(listener);
    }

    public void unregisterListner(final Listener listener) {
        listeners.remove(listener);
    }

    public void unregisterAllListener() {
        listeners.clear();
    }

    public void callEvent(final Event event) {
        listeners.stream()
                .forEach(e -> handleEvent(event, e));
    }

    public void registerEvent(Event event){
        this.events.add(event);
    }

    private void handleEvent(final Event event , final Listener listener) {
        if (event instanceof CloudStartedEvent)
            listener.handel((CloudStartedEvent) event);
        else if (event instanceof StartServerEvent)
            listener.handel((StartServerEvent) event);
        else if (event instanceof StopServerEvent)
            listener.handel((StopServerEvent) event);
        else if (event instanceof StopProxyEvent)
            listener.handel((StopProxyEvent) event);
        else if (event instanceof StartProxyEvent)
            listener.handel((StartProxyEvent)event);
        else
            throw new IllegalStateException("Cannot resole Event Type, or its undefined");
    }
}
