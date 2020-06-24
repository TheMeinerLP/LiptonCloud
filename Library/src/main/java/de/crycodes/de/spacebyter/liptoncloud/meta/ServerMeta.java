package de.crycodes.de.spacebyter.liptoncloud.meta;

import java.io.Serializable;
import java.util.Objects;

public class ServerMeta implements Serializable {

    private final String serverName;
    private final Integer id;
    private final ServerGroupMeta serverGroupMeta;
    private final String wrapperID;

    public ServerMeta(String serverName, Integer id, ServerGroupMeta serverGroupMeta, String wrapperID) {
        this.serverName = serverName;
        this.id = id;
        this.serverGroupMeta = serverGroupMeta;
        this.wrapperID = wrapperID;
    }

    public String getServerName() {
        return serverName;
    }

    public Integer getId() {
        return id;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    @Override
    public String toString() {
        return "ServerMeta{" +
                "serverName='" + serverName + '\'' +
                ", id=" + id +
                ", serverGroupMeta=" + serverGroupMeta +
                ", wrapperID='" + wrapperID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerMeta that = (ServerMeta) o;
        return Objects.equals(serverName, that.serverName) &&
                Objects.equals(id, that.id) &&
                Objects.equals(serverGroupMeta, that.serverGroupMeta) &&
                Objects.equals(wrapperID, that.wrapperID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverName, id, serverGroupMeta, wrapperID);
    }
}
