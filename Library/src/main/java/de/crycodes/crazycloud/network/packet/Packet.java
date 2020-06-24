package de.crycodes.crazycloud.network.packet;

import java.io.Serializable;
import java.util.Objects;

/*
 * Created by CryCodes on 25.03.2020
 * Project: CrazyCloud
 * Copyright: Nils Schrock | ERAPED.net
 */

public class Packet implements Serializable {

    private final String id;

    public Packet(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Packet objects1 = (Packet) o;
        return Objects.equals(id, objects1.id);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id);
        return result;
    }

    public String getId() {
        return id;
    }
}
