package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coded By CryCodes
 * Class: IDManager
 * Date : 25.06.2020
 * Time : 14:22
 * Project: LiptonCloud
 */

public class IDManager {

    private ConcurrentHashMap<String, List<Integer>> serverIdList = new ConcurrentHashMap<>();

    public int getFreeID(String serverGroupMeta){
        if (!this.serverIdList.containsKey(serverGroupMeta)){
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            this.serverIdList.put(serverGroupMeta, ids);
            return 1;
        } else {
            for (int i = 1; i < 2000; i++){
                if (this.serverIdList.get(serverGroupMeta).contains(i))
                    continue;
                else {
                    this.serverIdList.get(serverGroupMeta).add(i);
                    return i;
                }
            }
        }
        return 404;
    }
    public void removeID(String serverGroupMeta, int id){
        List<Integer> ids = serverIdList.get(serverGroupMeta);

        ids.remove(id - 1);

        this.serverIdList.remove(serverGroupMeta);
        this.serverIdList.put(serverGroupMeta, ids);

    }

    public ConcurrentHashMap<String, List<Integer>> getServerIdList() {
        return serverIdList;
    }


}
