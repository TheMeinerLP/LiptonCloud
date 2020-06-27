package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;

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

    public void registerProxy(ProxyMeta serverMeta, CallBack<Boolean> isAuthenticated){
        if (globalProxyList.contains(serverMeta)) {
            isAuthenticated.accept(false);
            return;
        }
        globalProxyList.add(serverMeta);
        LiptonMaster.getInstance().getColouredConsoleProvider().info("Registered new Proxy: " + serverMeta.getName());
        isAuthenticated.accept(true);
    }
    public void unregisterProxy(ProxyMeta serverMeta){
        if (!globalProxyList.contains(serverMeta))return;
        globalProxyList.remove(serverMeta);
        LiptonMaster.getInstance().getColouredConsoleProvider().info("Registered Proxy: " + serverMeta.getName());
    }

    public void unregisterProxy(ProxyMeta meta, Object o) {
    }

    public List<ProxyMeta> getGlobalProxyList() {
        return globalProxyList;
    }
}
