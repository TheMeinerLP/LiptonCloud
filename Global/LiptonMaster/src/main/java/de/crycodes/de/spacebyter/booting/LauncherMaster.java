package de.crycodes.de.spacebyter.booting;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;
import org.fusesource.jansi.AnsiConsole;

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
        AnsiConsole.systemInstall();
        new LiptonMaster();
    }
    //</editor-fold>

}
