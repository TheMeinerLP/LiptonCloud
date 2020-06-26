package de.crycodes.de.spacebyter.liptoncloud.meta;

import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ServerMeta implements Serializable, Meta {

    private  String serverName;
    private  Integer id;
    private  ServerGroupMeta serverGroupMeta;
    private  String wrapperID;
    private  String host;
    private  Integer port;

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

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setServerGroupMeta(ServerGroupMeta serverGroupMeta) {
        this.serverGroupMeta = serverGroupMeta;
    }

    public void setWrapperID(String wrapperID) {
        this.wrapperID = wrapperID;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
