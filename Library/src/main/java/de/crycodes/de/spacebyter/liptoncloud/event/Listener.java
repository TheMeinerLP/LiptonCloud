package de.crycodes.de.spacebyter.liptoncloud.event;
/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


import de.crycodes.de.spacebyter.liptoncloud.event.enums.EventTargetType;

public abstract class Listener {

    public Listener(String name, EventTargetType eventTargetType) {
        this.name = name;
        this.eventTargetType = eventTargetType;
    }

    private String name;
    private EventTargetType eventTargetType;

    //TODO: HANDEL METHOD

    public String getName() {
        return name;
    }

    public EventTargetType getEventTargetType() {
        return eventTargetType;
    }
}


