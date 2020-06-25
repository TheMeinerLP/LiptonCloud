package de.crycodes.examples.addon;

import de.crycodes.de.spacebyter.addon.MasterAddon;
import de.crycodes.de.spacebyter.liptoncloud.event.enums.EventTargetType;
import de.crycodes.examples.addon.commands.TestCommand;
import de.crycodes.examples.addon.event.TestEvent;
import de.crycodes.examples.addon.listener.TestListener;

/**
 * Coded By CryCodes
 * Class: ExampleAddon
 * Date : 25.06.2020
 * Time : 15:44
 * Project: LiptonCloud
 */

public class ExampleAddon extends MasterAddon {

    @Override
    public void onLoading() {

        getInternalCloudSystem().getColouredConsoleProvider().info("Starting Addon: " + this.getAddonName());

        //REGISTER COMMAND
        registerCommand(new TestCommand("test", "Test Command from addon!", new String[]{"lol", "eintest"}));
        //REGISTER EVENT
        registerEvent(new TestEvent(getInternalCloudSystem().getColouredConsoleProvider()));
        //REGISTER LISTENER
        registerListener(new TestListener("TestListner", EventTargetType.NOT_DEFINED));


        super.onLoading();
    }

    @Override
    public void onPrepare() {
        super.onPrepare();
    }

    @Override
    public void onReadyToClose() {
        getInternalCloudSystem().getColouredConsoleProvider().info("Stopping Addon: " + this.getAddonName());
        super.onReadyToClose();
    }


}
