package de.crycodes.de.spacebyter.proxy;

import de.crycodes.de.spacebyter.LiptonMaster;

import java.io.IOException;

/**
 * Coded By CryCodes
 * Class: BungeeCordManager
 * Date : 03.07.2020
 * Time : 14:32
 * Project: LiptonCloud
 */

public class BungeeCordManager {

    private BungeeStartUpHandler bungeeStartUpHandler;

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="BungeeCordManager">
    public BungeeCordManager(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
        try {
            bungeeStartUpHandler = new BungeeStartUpHandler(this.liptonMaster);
            bungeeStartUpHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="getBungeeStartUpHandler">
    public BungeeStartUpHandler getBungeeStartUpHandler() {
        return bungeeStartUpHandler;
    }
    //</editor-fold>
}
