package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;

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

    public void registerServer(ServerMeta serverMeta){
        if (globalServerList.contains(serverMeta))return;
        globalServerList.add(serverMeta);
    }
    public void unregisterServer(ServerMeta serverMeta){
        if (!globalServerList.contains(serverMeta))return;
        globalServerList.remove(serverMeta);
    }

}
