package de.crycodes.testmodule;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.addon.MasterModule;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import de.crycodes.de.spacebyter.liptoncloud.addon.CloudModule;
import de.crycodes.testmodule.commands.TestCommand;
import de.crycodes.testmodule.events.TestEvent;
import de.crycodes.testmodule.listener.TestListener;

/**
 * Coded By CryCodes
 * Class: TestModule
 * Date : 17.07.2020
 * Time : 11:48
 * Project: LiptonCloud
 */

public class TestModule extends MasterModule {

    public void onEnable() {
        registerCommand(new TestCommand("Test", "TEST LOL EY", "LOL"));
        registerListener(new TestListener());

        try {
            Thread.sleep(2000);

            callEvent(new TestEvent("TEST"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public void onDisable() { }
}
