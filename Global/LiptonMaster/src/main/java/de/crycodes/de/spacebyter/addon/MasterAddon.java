package de.crycodes.de.spacebyter.addon;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.LiptonMasterService;
import de.crycodes.de.spacebyter.liptoncloud.addon.JavaAddon;

/**
 * Coded By CryCodes
 * Class: MasterAddon
 * Date : 16.07.2020
 * Time : 18:45
 * Project: LiptonCloud
 */

public class MasterAddon extends JavaAddon<LiptonMaster> {

    //<editor-fold desc="getInternalCloudSystem">
    @Override
    @Deprecated
    public LiptonMaster getInternalCloudSystem() {
        return LiptonMasterService.getInternalCloudMaster();
    }
    //</editor-fold>

}
