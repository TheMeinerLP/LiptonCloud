package de.crycodes.de.spacebyter.liptoncloud.event;
/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import de.crycodes.de.spacebyter.liptoncloud.event.enums.EventTargetType;
import de.crycodes.de.spacebyter.liptoncloud.event.utils.Event;

import java.util.ArrayList;
import java.util.List;

public final class EventManager {
    private final List<Listener> listeners = new ArrayList<>();

    public void registerListener(final Listener listener) {
        listeners.add(listener);
    }

    public void unregisterListner(final Listener listener) {
        listeners.remove(listener);
    }

    public void unregisterAllListener() {
        listeners.clear();
    }

    public void callEvent(final EventTargetType eventTargetType, final Event event) {
        listeners.stream()
                .filter(e -> e.getEventTargetType().equals(eventTargetType))
                .forEach(e -> handleEvent(event, eventTargetType.name(), e));
    }

    private void handleEvent(final Event event, final String name, final Listener listener) {
        if (true /*TODO: ASK EVENT*/);

        else
            throw new IllegalStateException("Cannot resole Event Type, or its undefined");
    }
}
