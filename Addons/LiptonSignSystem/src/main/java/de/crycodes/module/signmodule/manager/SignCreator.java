package de.crycodes.module.signmodule.manager;


import de.crycodes.module.signmodule.config.SignConfig;
import de.crycodes.module.signmodule.objects.CloudSign;
import de.crycodes.module.signmodule.objects.SignGroup;

import java.util.HashMap;

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
