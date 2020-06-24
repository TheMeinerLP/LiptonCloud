package de.crycodes.de.spacebyter.liptoncloud.utils;

import java.io.Serializable;

public final class ExitUtil implements Serializable {

    /**
     * All exit statuses of the cloud system
     */
    public static final int
        STARTED_AS_ROOT = 0,
        STOPPED_SUCESS = 1,
        NOT_JAVA_8 = 2,
        CONTROLLERKEY_MISSING = 3,
        TERMINATED = 4,
        CONTROLLER_NOT_REACHABLE = 5,
        VERSION_UPDATE = 6;
}
