package de.crycodes.de.spacebyter.liptoncloud.utils.map.maps;

import de.crycodes.de.spacebyter.liptoncloud.utils.map.MapUtility;

import java.io.Serializable;

public final class Double<F, S> implements Serializable {

    private static final long serialVersionUID = -8210889894016298745L;

    /**
     * The first value of the map
     */
    private F first;

    /**
     * The second value of the map
     */
    private S second;

    @java.beans.ConstructorProperties({"first", "second"})
    public Double(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Checks if a specific parameter is in the double
     *
     * @param toFind The key which should be checked for
     * @param <T> The type of the key
     * @return If the double contains the key
     */
    public <T> boolean contains(T toFind) {
        return MapUtility.contains(this, toFind);
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }
}
