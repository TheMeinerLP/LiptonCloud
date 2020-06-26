package de.crycodes.addon.cloudfaler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.addon.MasterAddon;

/**
 * Coded By CryCodes
 * Class: CloudFlare
 * Date : 26.06.2020
 * Time : 10:03
 * Project: LiptonCloud
 */

public class CloudFlare extends MasterAddon {

    private static CloudFlare instance;


    @Override
    public void onLoading() {
        instance = this;
        new CloudFlareUtil(LiptonMaster.getInstance());
        //TODO: REGISTER LISTENER
    }

    @Override
    public void onReadyToClose() {
        CloudFlareUtil.getInstance().shutdown();
       //TODO: UNREGISTER LISTENER
    }


    public static CloudFlare getInstance() {
        return instance;
    }
}
