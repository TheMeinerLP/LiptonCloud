package de.crycodes.addon.cloudfaler;

import de.crycodes.addon.cloudfaler.listener.ServerHandlerListener;
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

    private CloudFlareUtil cloudFlareUtil;
    private ServerHandlerListener serverHandlerListener;

    @Override
    public void onLoading() {
        instance = this;
        cloudFlareUtil = new CloudFlareUtil(LiptonMaster.getInstance());

        serverHandlerListener = new ServerHandlerListener("ServerHandlerListener");

        LiptonMaster.getInstance().getEventManager().registerListener(serverHandlerListener);
    }

    @Override
    public void onReadyToClose() {
        CloudFlareUtil.getInstance().shutdown();
        LiptonMaster.getInstance().getEventManager().unregisterListner(serverHandlerListener);
    }


    public static CloudFlare getInstance() {
        return instance;
    }

    public CloudFlareUtil getCloudFlareUtil() {
        return cloudFlareUtil;
    }
}
