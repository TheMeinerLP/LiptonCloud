package de.crycodes.de.spacebyter.addon;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.LiptonMasterService;
import de.crycodes.de.spacebyter.liptoncloud.addon.CloudModule;

/**
 * Coded By CryCodes
 * Class: MasterAddon
 * Date : 16.07.2020
 * Time : 18:45
 * Project: LiptonCloud
 */

public abstract class MasterModule extends CloudModule<LiptonMaster> {

    @Override
    public LiptonMaster getService() {
        return LiptonMasterService.getInternalCloudMaster();
    }

}
