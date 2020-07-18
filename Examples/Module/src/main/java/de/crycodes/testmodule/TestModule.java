package de.crycodes.testmodule;

import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.CloudModule;

/**
 * Coded By CryCodes
 * Class: TestModule
 * Date : 17.07.2020
 * Time : 11:48
 * Project: LiptonCloud
 */

public class TestModule extends CloudModule<LiptonLibrary> {

    public void onEnable() {
        System.out.println("test lol!");
    }

    public void onDisable() { }
}