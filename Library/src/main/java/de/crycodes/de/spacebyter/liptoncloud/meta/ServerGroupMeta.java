package de.crycodes.de.spacebyter.liptoncloud.meta;

import de.crycodes.de.spacebyter.liptoncloud.interfaces.Meta;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ServerGroupMeta implements Serializable, Meta {

    private final String groupName;
    private final int maxMemory;
    private final int minMemory;
    private boolean dynamicService;
    private boolean maintenance;
    private final int maxServer;
    private final int minServer;

    public ServerGroupMeta(String groupName, int maxMemory, int minMemory, boolean dynamicService, boolean maintenance, int maxServer, int minServer) {
        this.groupName = groupName;
        this.maxMemory = maxMemory;
        this.minMemory = minMemory;
        this.dynamicService = dynamicService;
        this.maintenance = maintenance;
        this.maxServer = maxServer;
        this.minServer = minServer;
    }


    @Override
    public String toString() {
        return "ServerGroupMeta{" +
                "groupName='" + groupName + '\'' +
                ", maxMemory=" + maxMemory +
                ", minMemory=" + minMemory +
                ", dynamicService=" + dynamicService +
                ", maintenance=" + maintenance +
                ", maxServer=" + maxServer +
                ", minServer=" + minServer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerGroupMeta that = (ServerGroupMeta) o;
        return maxMemory == that.maxMemory &&
                minMemory == that.minMemory &&
                dynamicService == that.dynamicService &&
                maintenance == that.maintenance &&
                maxServer == that.maxServer &&
                minServer == that.minServer &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, maxMemory, minMemory, dynamicService, maintenance, maxServer, minServer);
    }

    public String getGroupName() {
        return groupName;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public int getMinMemory() {
        return minMemory;
    }

    public boolean isDynamicService() {
        return dynamicService;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public int getMaxServer() {
        return maxServer;
    }

    public int getMinServer() {
        return minServer;
    }

    public void setDynamicService(boolean dynamicService) {
        this.dynamicService = dynamicService;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }
}
