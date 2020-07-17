package de.crycodes.de.spacebyter.liptoncloud.event;
/*
 * Created by CryCodes on 21.02.2020
 * Project: ElixCloud
 * Copyright: Nils Schrock | ERAPED.net
 */


public abstract class Listener {

    public Listener(String name) {
        this.name = name;

    }

    private String name;

    public String getName() {
        return name;
    }

}


