package de.crycodes.de.spacebyter.network.channel;

import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: Identifier
 * Date : 29.05.2020
 * Time : 15:31
 * Project: CrazyCloud - V2
 */

public class Identifier {

    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + id + '\'' +
                '}';
    }
}
