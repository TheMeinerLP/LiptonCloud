package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import com.google.gson.internal.LinkedTreeMap;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.SignGroup;
import org.checkerframework.checker.units.qual.C;

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

    public SignGroup getSignGroup(String group){
        if (signConfig.getSignGroupAfterName(group) == null)
            return new SignGroup(group.toUpperCase());
        return signConfig.getSignGroupAfterName(group);
    }

    public SignConfig getSignConfig() {
        return signConfig;
    }

    public void createSign(CloudSign sign, String group) {
        SignGroup signGroup;

        if (signConfig.getSignGroupAfterName(group) == null) {
            signGroup = new SignGroup(group.toUpperCase());
        } else {
            signGroup = this.signConfig.getSignGroupAfterName(group);
        }
        if (signGroup.getCloudSignHashMap().containsValue(sign)) return;

        HashMap<Integer, CloudSign> cloudSignHashMap = signGroup.getCloudSignHashMap();

        cloudSignHashMap.put(cloudSignHashMap.keySet().size() + 1, sign);

        this.signConfig.addOrReplace(signGroup);
    }
}
