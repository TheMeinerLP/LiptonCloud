package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ProxyManager
 * Date : 26.06.2020
 * Time : 12:16
 * Project: LiptonCloud
 */

public class ProxyManager {

    private final List<ProxyMeta> globalProxyList = new ArrayList<>();

    public void registerProxy(ProxyMeta serverMeta){
        if (globalProxyList.contains(serverMeta))return;
        globalProxyList.add(serverMeta);
    }
    public void unregisterProxy(ProxyMeta serverMeta){
        if (!globalProxyList.contains(serverMeta))return;
        globalProxyList.remove(serverMeta);
    }

}
