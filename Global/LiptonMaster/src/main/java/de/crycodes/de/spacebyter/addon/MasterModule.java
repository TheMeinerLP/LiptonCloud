package de.crycodes.de.spacebyter.addon;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.addon.CloudModule;

/**
 * Coded By CryCodes
 * Class: MasterModule
 * Date : 18.07.2020
 * Time : 15:10
 * Project: LiptonCloud
 */

public abstract class MasterModule extends CloudModule<LiptonMaster> {

    @Override
    public LiptonMaster getService() {
        return LiptonMaster.getInstance();
    }

}
