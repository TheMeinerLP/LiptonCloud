package de.crycodes.de.spacebyter.addon;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.addon.CloudModule;
import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;

/**
 * Coded By CryCodes
 * Class: MasterModule
 * Date : 18.07.2020
 * Time : 15:10
 * Project: LiptonCloud
 */

public abstract class MasterModule extends CloudModule {

    @Override
    public EventManager getEventManager() {
        return LiptonMaster.getInstance().getEventManager();
    }

    @Override
    public Scheduler getScheduler() {
        return LiptonMaster.getInstance().getScheduler();
    }

    @Override
    public ColouredConsoleProvider getLogger() {
        return LiptonMaster.getInstance().getColouredConsoleProvider();
    }

    @Override
    public void registerListener(Listener listener) {
       LiptonMaster.getInstance().getEventManager().registerListener(listener);
    }

    @Override
    public void callEvent(Event event) {
        LiptonMaster.getInstance().getEventManager().callEvent(event);
    }

    @Override
    public void registerCommand(ModuleCommand command) {
        LiptonMaster.getInstance().getModuleCommandManager().registerCommand(command);
    }

}
