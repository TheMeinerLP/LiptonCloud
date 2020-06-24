package de.crycodes.de.spacebyter.liptoncloud.utils.runtime;

import java.io.Serializable;

public interface Shutdown extends Serializable {

    /**
     * Shutdowns something
     */
    void shutdownAll();
}
