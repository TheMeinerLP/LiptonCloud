package de.crycodes.de.spacebyter.liptoncloud.meta;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ServerGroupMeta implements Serializable {

    private final String groupName;
    private final int maxMemory;
    private final int minMemory;
    private final boolean dynamicService;
    private final boolean maintenance;
    private final int maxServer;
    private final int minServer;
    private final List<Integer> usedIDS;

    public ServerGroupMeta(String groupName, int maxMemory, int minMemory, boolean dynamicService, boolean maintenance, int maxServer, int minServer, List<Integer> usedIDS) {
        this.groupName = groupName;
        this.maxMemory = maxMemory;
        this.minMemory = minMemory;
        this.dynamicService = dynamicService;
        this.maintenance = maintenance;
        this.maxServer = maxServer;
        this.minServer = minServer;
        this.usedIDS = usedIDS;
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
                ", usedIDS=" + usedIDS +
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
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(usedIDS, that.usedIDS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, maxMemory, minMemory, dynamicService, maintenance, maxServer, minServer, usedIDS);
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

    public List<Integer> getUsedIDS() {
        return usedIDS;
    }
}
