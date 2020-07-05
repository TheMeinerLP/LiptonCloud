package de.crycodes.de.spacebyter.proxy;

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

    public BungeeCordManager() {
        try {
            bungeeStartUpHandler = new BungeeStartUpHandler();
            bungeeStartUpHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BungeeStartUpHandler getBungeeStartUpHandler() {
        return bungeeStartUpHandler;
    }
}
