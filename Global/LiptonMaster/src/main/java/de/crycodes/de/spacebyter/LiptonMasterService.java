package de.crycodes.de.spacebyter;

import java.lang.annotation.Target;
import java.lang.reflect.Type;

/**
 * Coded By CryCodes
 * Class: LiptonMasterService
 * Date : 16.07.2020
 * Time : 18:51
 * Project: LiptonCloud
 */

public class LiptonMasterService {

    //<editor-fold desc="object">
    @Deprecated
    private static LiptonMaster liptonMaster;
    //</editor-fold>

    //<editor-fold desc="LiptonMasterService">
    public LiptonMasterService() {
        liptonMaster = new LiptonMaster();
    }
    //</editor-fold>

    //<editor-fold desc="getInternalCloudMaster">
    @Deprecated
    public static LiptonMaster getInternalCloudMaster(){
        return liptonMaster;
    }
    //</editor-fold>

}
