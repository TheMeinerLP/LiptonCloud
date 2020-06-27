package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.CallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: ServerGlobalManager
 * Date : 26.06.2020
 * Time : 12:02
 * Project: LiptonCloud
 */

public class ServerGlobalManager {

    private final List<ServerMeta> globalServerList = new ArrayList<>();

    public void registerServer(ServerMeta serverMeta, CallBack<Boolean> isAuthenticated){
        if (globalServerList.contains(serverMeta)) {
            if (serverMeta.getServerName().equalsIgnoreCase("NONE")){
                isAuthenticated.accept(true);
                LiptonMaster.getInstance().getColouredConsoleProvider().info("Registered new (Dummy) Server: " + serverMeta.getServerName());
                LiptonMaster.getInstance().getServerManager().getGlobalserverrlist().add(serverMeta);
                return;

            }
            isAuthenticated.accept(false);
            return;
        }
        globalServerList.add(serverMeta);
        LiptonMaster.getInstance().getServerManager().getGlobalserverrlist().add(serverMeta);
        LiptonMaster.getInstance().getColouredConsoleProvider().info("Registered new Server: " + serverMeta.getServerName());
        isAuthenticated.accept(true);
    }
    public void unregisterServer(ServerMeta serverMeta){
        if (!globalServerList.contains(serverMeta))return;
        globalServerList.remove(serverMeta);
        LiptonMaster.getInstance().getColouredConsoleProvider().info("UnRegistered Server: " + serverMeta.getServerName());
    }

    public List<ServerMeta> getGlobalServerList() {
        return globalServerList;
    }
}
