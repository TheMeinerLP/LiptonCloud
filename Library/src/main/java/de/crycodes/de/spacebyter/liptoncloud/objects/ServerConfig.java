package de.crycodes.de.spacebyter.liptoncloud.objects;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;

import java.util.List;

/**
 * Coded By CryCodes
 * Class: ServerConfig
 * Date : 26.06.2020
 * Time : 09:06
 * Project: LiptonCloud
 */

public class ServerConfig {

    private final List<ServerMeta> globalServers;
    private final List<WrapperMeta> globalWrappers;
    private final List<ProxyMeta> globalProxys;
    private final List<ServerGroupMeta> globalServerGroups;
    private final List<String> cloudAdminPlayers;

    public ServerConfig(List<ServerMeta> globalServers, List<WrapperMeta> globalWrappers, List<ProxyMeta> globalProxys, List<ServerGroupMeta> globalServerGroups, List<String> cloudAdminPlayers) {
        this.globalServers = globalServers;
        this.globalWrappers = globalWrappers;
        this.globalProxys = globalProxys;
        this.globalServerGroups = globalServerGroups;
        this.cloudAdminPlayers = cloudAdminPlayers;
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

    public List<ServerGroupMeta> getGlobalServerGroups() {
        return globalServerGroups;
    }

    public List<String> getCloudAdminPlayers() {
        return cloudAdminPlayers;
    }

}
