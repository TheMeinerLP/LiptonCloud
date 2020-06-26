package de.crycodes.de.spacebyter.liptoncloud.objects;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

import java.util.List;

/**
 * Coded By CryCodes
 * Class: ProxyConfig
 * Date : 26.06.2020
 * Time : 09:06
 * Project: LiptonCloud
 */

public class ProxyConfig {

    private final List<ServerMeta> globalServers;
    private final List<WrapperMeta> globalWrappers;
    private final List<ProxyMeta> globalProxys;
    private final Boolean isMaintenance;
    private final Boolean isAutosStart;
    private final List<String> maintenancePlayer;
    private final List<ServerGroupMeta> globalServerGroups;
    private final List<String> cloudAdminPlayers;
    private final String tablist_top;
    private final String tablist_bottom;
    private final String default_motd;
    private final String maintenance_motd;

    public ProxyConfig(List<ServerMeta> globalServers, List<WrapperMeta> globalWrappers, List<ProxyMeta> globalProxys, Boolean isMaintenance, Boolean isAutosStart, List<String> maintenancePlayer, List<ServerGroupMeta> globalServerGroups, List<String> cloudAdminPlayers, String tablist_top, String tablist_bottom, String default_motd, String maintenance_motd) {
        this.globalServers = globalServers;
        this.globalWrappers = globalWrappers;
        this.globalProxys = globalProxys;
        this.isMaintenance = isMaintenance;
        this.isAutosStart = isAutosStart;
        this.maintenancePlayer = maintenancePlayer;
        this.globalServerGroups = globalServerGroups;
        this.cloudAdminPlayers = cloudAdminPlayers;
        this.tablist_top = tablist_top;
        this.tablist_bottom = tablist_bottom;
        this.default_motd = default_motd;
        this.maintenance_motd = maintenance_motd;
    }

    public List<ServerMeta> getGlobalServers() {
        return globalServers;
    }

    public List<WrapperMeta> getGlobalWrappers() {
        return globalWrappers;
    }

    public List<ProxyMeta> getGlobalProxys() {
        return globalProxys;
    }

    public Boolean getMaintenance() {
        return isMaintenance;
    }

    public Boolean getAutosStart() {
        return isAutosStart;
    }

    public List<String> getMaintenancePlayer() {
        return maintenancePlayer;
    }

    public List<ServerGroupMeta> getGlobalServerGroups() {
        return globalServerGroups;
    }

    public List<String> getCloudAdminPlayers() {
        return cloudAdminPlayers;
    }

    public String getTablist_top() {
        return tablist_top;
    }

    public String getTablist_bottom() {
        return tablist_bottom;
    }

    public String getDefault_motd() {
        return default_motd;
    }

    public String getMaintenance_motd() {
        return maintenance_motd;
    }
}
