package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
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

    private final LiptonMaster liptonMaster;

    private final List<ProxyMeta> globalProxyList = new ArrayList<>();

    //<editor-fold desc="ProxyManager">
    public ProxyManager(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="register and unregister">
    public void registerProxy(ProxyMeta serverMeta, CallBack<Boolean> isAuthenticated){
        if (globalProxyList.contains(serverMeta)) {
            isAuthenticated.accept(false);
            return;
        }
        globalProxyList.add(serverMeta);
        liptonMaster.getColouredConsoleProvider().info("Registered new Proxy: " + serverMeta.getName());
        isAuthenticated.accept(true);
    }
    public void unregisterProxy(ProxyMeta serverMeta){
        if (!globalProxyList.contains(serverMeta))return;
        globalProxyList.remove(serverMeta);
        liptonMaster.getColouredConsoleProvider().info("Registered Proxy: " + serverMeta.getName());
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public void unregisterProxy(ProxyMeta meta, Object o) {
    }

    public List<ProxyMeta> getGlobalProxyList() {
        return globalProxyList;
    }
    //</editor-fold>
}
