package de.crycodes.de.spacebyter.liptoncloud.objects;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

import java.io.Serializable;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ProxyConfig
 * Date : 26.06.2020
 * Time : 09:06
 * Project: LiptonCloud
 */

public class ProxyConfig implements Serializable {

    private final List<ServerMeta> globalServers;
    private final List<WrapperMeta> globalWrappers;
    private final List<ProxyMeta> globalProxys;
    private final Boolean isMaintenance;
    private final List<String> maintenancePlayer;
    private final List<ServerGroupMeta> globalServerGroups;
    private final List<String> cloudAdminPlayers;
    private final String tablist_top;
    private final String tablist_bottom;
    private final String default_motd;
    private final String maintenance_motd;
    private final String maintenanceVersionString;
    private final int maxPlayer;
    private final boolean useProxyConfig;
    private final String maintenanceKickMessage;

    private final String server_start_message;
    private final String server_stop_message;
    private final String server_online_message;

    private final Boolean useNotify;

    public ProxyConfig(List<ServerMeta> globalServers, List<WrapperMeta> globalWrappers, List<ProxyMeta> globalProxys, boolean isMaintenance, List<String> maintenancePlayer, List<ServerGroupMeta> globalServerGroups, List<String> cloudAdminPlayers, String tablist_top, String tablist_bottom, String default_motd, String maintenance_motd, String maintenanceVersionString, int maxPlayer, boolean useProxyConfig, String maintenanceKickMessage, String server_start_message, String server_stop_message, String server_online_message, Boolean useNotify) {
        this.globalServers = globalServers;
        this.globalWrappers = globalWrappers;
        this.globalProxys = globalProxys;
        this.isMaintenance = isMaintenance;
        this.maintenancePlayer = maintenancePlayer;
        this.globalServerGroups = globalServerGroups;
        this.cloudAdminPlayers = cloudAdminPlayers;
        this.tablist_top = tablist_top;
        this.tablist_bottom = tablist_bottom;
        this.default_motd = default_motd;
        this.maintenance_motd = maintenance_motd;
        this.maintenanceVersionString = maintenanceVersionString;
        this.maxPlayer = maxPlayer;
        this.useProxyConfig = useProxyConfig;
        this.maintenanceKickMessage = maintenanceKickMessage;
        this.server_start_message = server_start_message;
        this.server_stop_message = server_stop_message;
        this.server_online_message = server_online_message;
        this.useNotify = useNotify;
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

    public String getMaintenanceVersionString() {
        return maintenanceVersionString;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean isUseProxyConfig() {
        return useProxyConfig;
    }

    public String getMaintenanceKickMessage() {
        return maintenanceKickMessage;
    }

    public String getServer_start_message() {
        return server_start_message;
    }

    public String getServer_stop_message() {
        return server_stop_message;
    }

    public String getServer_online_message() {
        return server_online_message;
    }

    public Boolean getUseNotify() {
        return useNotify;
    }
}
