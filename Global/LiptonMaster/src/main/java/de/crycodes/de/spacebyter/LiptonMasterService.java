package de.crycodes.de.spacebyter;

import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/**
 * Coded By CryCodes
 * Class: LiptonMasterService
 * Date : 16.07.2020
 * Time : 18:51
 * Project: LiptonCloud
 */

public class LiptonMasterService {

    //<editor-fold desc="instance">
    private static LiptonMasterService instance;
    //</editor-fold>

    //<editor-fold desc="LiptonMasterService">
    public LiptonMasterService() throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        instance = this;
        new LiptonMaster();

    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public static LiptonMasterService getInstance() {
        return instance;
    }
    //</editor-fold>
}
