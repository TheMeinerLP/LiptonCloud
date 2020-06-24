package de.crycodes.de.spacebyter.liptoncloud.event.enums;

/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


public enum EventTargetType {

    NOT_DEFINED(0, null);

    EventTargetType(final int id, final String abstractName) {
        this.id = id;
        this.name = abstractName;
    }

    private final int id;
    private final String name;
}
