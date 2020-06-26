package de.crycodes.de.spacebyter.liptoncloud.objects;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
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

    public ProxyConfig(List<ServerMeta> globalServers, List<WrapperMeta> globalWrappers, List<ProxyMeta> globalProxys) {
        this.globalServers = globalServers;
        this.globalWrappers = globalWrappers;
        this.globalProxys = globalProxys;
    }

    public List<ProxyMeta> getGlobalProxys() {
        return globalProxys;
    }

    public List<ServerMeta> getGlobalServers() {
        return globalServers;
    }

    public List<WrapperMeta> getGlobalWrappers() {
        return globalWrappers;
    }
}
