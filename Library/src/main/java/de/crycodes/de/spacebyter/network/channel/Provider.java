package de.crycodes.de.spacebyter.network.channel;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: Provider
 * Date : 29.05.2020
 * Time : 15:32
 * Project: CrazyCloud - V2
 */

public class Provider {

    private final String prov;

    public Provider(String prov) {
        this.prov = prov;
    }

    public String getProv() {
        return prov;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider that = (Provider) o;
        return Objects.equals(prov, that.prov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prov);
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + prov + '\'' +
                '}';
    }
}