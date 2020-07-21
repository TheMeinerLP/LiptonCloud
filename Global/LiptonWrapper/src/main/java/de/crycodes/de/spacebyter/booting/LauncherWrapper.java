package de.crycodes.de.spacebyter.booting;

import de.crycodes.de.spacebyter.LiptonWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Coded By CryCodes
 * Class: LauncherMaster
 * Date : 24.06.2020
 * Time : 10:29
 * Project: LiptonCloud
 */

public class LauncherWrapper {

    //<editor-fold desc="Main Method">
    public static void main(String[] args) {
        try {
            new LiptonWrapper();
        } catch (IOException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>
}
