package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;

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

    public void createSign(CloudSign cloudSign){
        final String group = cloudSign.getServerGroupName().toUpperCase();

        HashMap<Integer,CloudSign> signs = this.signConfig.getSignsAfterGroup(group);

        signs.put(signs.size() + 1,cloudSign);

        this.signConfig.insertUpdatedSigns(signs,group);
    }

    public CloudSign getSignByIdAndGroup(String group, Integer id){
        if (this.signConfig.getSignsAfterGroup(group) == null) System.out.println("NULL SERVERGROUP NOT FOUND");
        HashMap<Integer,CloudSign> signs = this.signConfig.getSignsAfterGroup(group.toUpperCase());
        return signs.get(id);
    }

    public SignConfig getSignConfig() {
        return signConfig;
    }
}
