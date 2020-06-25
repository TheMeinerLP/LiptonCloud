package de.crycodes.addon.signsystem.enums;

import java.io.Serializable;

/**
 * Coded By CryCodes
 * Class: SignState
 * Date : 25.06.2020
 * Time : 16:22
 * Project: LiptonCloud
 */

public enum SignState implements Serializable {

    ONLINE(1),
    OFFLINE(0),
    INGAME(2),
    STARTING(3),
    RESTARTING(4),
    UNKNOWN(404);

    private final Integer id;

    SignState(Integer id) {
        this.id = id;
    }

}
