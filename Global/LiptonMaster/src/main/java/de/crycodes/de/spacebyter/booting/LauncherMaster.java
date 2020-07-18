package de.crycodes.de.spacebyter.booting;

import de.crycodes.de.spacebyter.LiptonMasterService;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Coded By CryCodes
 * Class: LauncherMaster
 * Date : 16.07.2020
 * Time : 19:02
 * Project: LiptonCloud
 */

public class LauncherMaster {

    //<editor-fold desc="Main Method">
    @ShouldRunAsync
    public static void main(String[] args) {
        try {
            new LiptonMasterService();
        } catch (IOException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

}
