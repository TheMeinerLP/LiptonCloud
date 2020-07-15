package de.crycodes.de.spacebyter.manager;

import scala.Int;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coded By CryCodes
 * Class: IDManager
 * Date : 25.06.2020
 * Time : 14:22
 * Project: LiptonCloud
 */

public class IDManager {

    private ConcurrentHashMap<String, Map<Integer, Integer>> serverIdList = new ConcurrentHashMap<>();

    public int getFreeID(String serverGroupMeta){
        if (!this.serverIdList.containsKey(serverGroupMeta)){
            Map<Integer,Integer> ids = new HashMap();
            ids.put(1, 1);
            this.serverIdList.put(serverGroupMeta, ids);
            return 1;
        } else {
            for (int i = 1; i < 2000; i++){
                if (this.serverIdList.get(serverGroupMeta).containsKey(i))
                    continue;
                else {
                    this.serverIdList.get(serverGroupMeta).put(i, i);
                    return i;
                }
            }
        }
        return 404;
    }
    public void removeID(String serverGroupMeta, int id){
        Map<Integer,Integer> ids = serverIdList.get(serverGroupMeta);

        ids.remove(id - 1);

        this.serverIdList.remove(serverGroupMeta);
        this.serverIdList.put(serverGroupMeta, ids);

    }

    public ConcurrentHashMap<String, Map<Integer, Integer>> getServerIdList() {
        return serverIdList;
    }


}
