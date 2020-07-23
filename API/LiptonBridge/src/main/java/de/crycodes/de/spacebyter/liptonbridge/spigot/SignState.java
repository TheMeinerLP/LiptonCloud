package de.crycodes.de.spacebyter.liptonbridge.spigot;

import java.io.Serializable;

/**
 * Coded By CryCodes
 * Class: SignState
 * Date : 23.07.2020
 * Time : 18:42
 * Project: LiptonCloud
 */

public enum SignState implements Serializable {

    ONLINE(1),
    OFFLINE(2),
    LOADING(3),
    LOBBY(4),
    INGAME(5),
    MAINTENANCE(6),
    UNKNOWN(7);

    private Integer id;

    SignState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}