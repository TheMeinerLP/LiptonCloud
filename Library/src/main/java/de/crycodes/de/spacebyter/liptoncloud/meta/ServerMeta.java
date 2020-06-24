package de.crycodes.de.spacebyter.liptoncloud.meta;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ServerMeta implements Serializable {

    private final String serverName;
    private final Integer id;
    private final ServerGroupMeta serverGroupMeta;
    private final String wrapperID;
    private final String host;
    private final Integer port;

    public ServerMeta(String serverName, Integer id, ServerGroupMeta serverGroupMeta, String wrapperID, String host, Integer port) {
        this.serverName = serverName;
        this.id = id;
        this.serverGroupMeta = serverGroupMeta;
        this.wrapperID = wrapperID;
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerMeta{" +
                "serverName='" + serverName + '\'' +
                ", id=" + id +
                ", serverGroupMeta=" + serverGroupMeta +
                ", wrapperID='" + wrapperID + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
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
                Objects.equals(wrapperID, that.wrapperID) &&
                Objects.equals(host, that.host) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverName, id, serverGroupMeta, wrapperID, host, port);
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

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }
}
