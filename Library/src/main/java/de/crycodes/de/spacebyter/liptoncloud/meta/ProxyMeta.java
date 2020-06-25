package de.crycodes.de.spacebyter.liptoncloud.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Coded By CryCodes
 * Class: ProxyMeta
 * Date : 25.06.2020
 * Time : 14:14
 * Project: LiptonCloud
 */

public class ProxyMeta implements Serializable {

    private final String name;
    private final Integer id;
    private boolean fallbackproxy;
    private final boolean mainproxy;
    private List<String> serverlist;

    public ProxyMeta(String name, Integer id, boolean mainproxy) {
        this.name = name;
        this.id = id;
        this.mainproxy = mainproxy;
        this.fallbackproxy = false;
        this.serverlist = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ProxyMeta{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", fallbackproxy=" + fallbackproxy +
                ", mainproxy=" + mainproxy +
                ", serverlist=" + serverlist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyMeta proxyMeta = (ProxyMeta) o;
        return fallbackproxy == proxyMeta.fallbackproxy &&
                mainproxy == proxyMeta.mainproxy &&
                Objects.equals(name, proxyMeta.name) &&
                Objects.equals(id, proxyMeta.id) &&
                Objects.equals(serverlist, proxyMeta.serverlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, fallbackproxy, mainproxy, serverlist);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public boolean isFallbackproxy() {
        return fallbackproxy;
    }

    public boolean isMainproxy() {
        return mainproxy;
    }

    public List<String> getServerlist() {
        return serverlist;
    }
}
