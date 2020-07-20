package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import com.google.gson.internal.LinkedTreeMap;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Coded By CryCodes
 * Class: SignCreator
 * Date : 20.07.2020
 * Time : 16:03
 * Project: LiptonCloud
 */

public class SignCreator {

    private final SignConfig signConfig;

    public SignCreator(SignConfig signConfig) {
        this.signConfig = signConfig;
    }

    public void createSign(CloudSign cloudSign,String group){

        HashMap<Integer, LinkedTreeMap<String, Object>> signs = this.signConfig.getSignsAfterGroup(group);

        LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
        map.put("world", cloudSign.getWorld());
        map.put("x", cloudSign.getX());
        map.put("y", cloudSign.getY());
        map.put("z", cloudSign.getZ());

        signs.put(signs.size() + 1, map);

        this.signConfig.insertUpdatedSigns(signs,group);
    }

    public SignConfig getSignConfig() {
        return signConfig;
    }
}
