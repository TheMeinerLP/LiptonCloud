package de.crycodes.de.spacebyter.liptoncloud.player.meta;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Coded By CryCodes
 * Class: LiptonPlayer
 * Date : 17.07.2020
 * Time : 12:22
 * Project: LiptonCloud
 */

public class LiptonPlayer implements Serializable {

        private String name;
        private UUID uuid;
        private long first_login;
        private long last_login;

    public LiptonPlayer(String name, UUID uuid, long first_login, long last_login) {
        this.name = name;
        this.uuid = uuid;
        this.first_login = first_login;
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "LiptonPlayer{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", first_login=" + first_login +
                ", last_login=" + last_login +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiptonPlayer that = (LiptonPlayer) o;
        return first_login == that.first_login &&
                last_login == that.last_login &&
                Objects.equals(name, that.name) &&
                Objects.equals(uuid, that.uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getFirst_login() {
        return first_login;
    }

    public void setFirst_login(long first_login) {
        this.first_login = first_login;
    }

    public long getLast_login() {
        return last_login;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }
}