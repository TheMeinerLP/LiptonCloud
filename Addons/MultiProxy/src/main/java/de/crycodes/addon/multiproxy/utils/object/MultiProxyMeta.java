package de.crycodes.addon.multiproxy.utils.object;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Coded By CryCodes
 * Class: MultiProxyMeta
 * Date : 27.06.2020
 * Time : 15:04
 * Project: LiptonCloud
 */

public class MultiProxyMeta implements Serializable {

    private final ServerGroupMeta serverGroupMeta;
    private final ProxyMeta proxyMeta;
    private final UUID proxyUUID;

    public MultiProxyMeta(ServerGroupMeta serverGroupMeta, ProxyMeta proxyMeta, UUID proxyUUID) {
        this.serverGroupMeta = serverGroupMeta;
        this.proxyMeta = proxyMeta;
        this.proxyUUID = proxyUUID;
    }

    @Override
    public String toString() {
        return "MultiProxyMeta{" +
                "serverGroupMeta=" + serverGroupMeta +
                ", proxyMeta=" + proxyMeta +
                ", proxyUUID=" + proxyUUID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiProxyMeta that = (MultiProxyMeta) o;
        return Objects.equals(serverGroupMeta, that.serverGroupMeta) &&
                Objects.equals(proxyMeta, that.proxyMeta) &&
                Objects.equals(proxyUUID, that.proxyUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverGroupMeta, proxyMeta, proxyUUID);
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }

    public ProxyMeta getProxyMeta() {
        return proxyMeta;
    }

    public UUID getProxyUUID() {
        return proxyUUID;
    }
}
