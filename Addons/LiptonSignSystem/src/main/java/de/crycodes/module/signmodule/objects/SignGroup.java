package de.crycodes.module.signmodule.objects;

import java.util.HashMap;

/**
 * Coded By CryCodes
 * Class: SignGroup
 * Date : 21.07.2020
 * Time : 15:16
 * Project: LiptonCloud
 */

public class SignGroup {

    private final String name;
    private HashMap<Integer, CloudSign> cloudSignHashMap;

    public SignGroup(String name) {
        this.name = name;
        this.cloudSignHashMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, CloudSign> getCloudSignHashMap() {
        return cloudSignHashMap;
    }

    public void setCloudSignHashMap(HashMap<Integer, CloudSign> cloudSignHashMap) {
        this.cloudSignHashMap = cloudSignHashMap;
    }


}
