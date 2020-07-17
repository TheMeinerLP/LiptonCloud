package de.crycodes.de.spacebyter.liptoncloud.player;

import de.crycodes.de.spacebyter.liptoncloud.player.config.LiptonCloudPlayerConfig;

import java.io.File;

/**
 * Coded By CryCodes
 * Class: LiptonPlayerManager
 * Date : 17.07.2020
 * Time : 12:36
 * Project: LiptonCloud
 */

public class LiptonPlayerManager {

    private final File configFile;

    private LiptonCloudPlayerConfig liptonCloudPlayerConfig;

    public LiptonPlayerManager(File configFile) {
        this.configFile = configFile;

        liptonCloudPlayerConfig = new LiptonCloudPlayerConfig(configFile);

    }
}
