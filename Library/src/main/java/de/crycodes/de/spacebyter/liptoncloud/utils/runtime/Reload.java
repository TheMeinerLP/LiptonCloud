package de.crycodes.de.spacebyter.liptoncloud.utils.runtime;

import java.io.Serializable;

public interface Reload extends Serializable {

    /**
     * This method get called to reload something
     *
     * @throws Throwable If any exception occurs will be catch here
     */
    void reloadAll() throws Throwable;
}
