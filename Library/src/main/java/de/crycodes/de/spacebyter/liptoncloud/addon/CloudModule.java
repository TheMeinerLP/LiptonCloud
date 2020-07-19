package de.crycodes.de.spacebyter.liptoncloud.addon;

import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;

/**
 * Coded By CryCodes
 * Class: CloudModule
 * Date : 17.07.2020
 * Time : 11:27
 * Project: LiptonCloud
 */

public abstract class CloudModule {

    public abstract ColouredConsoleProvider getLogger();
    public abstract EventManager getEventManager();
    public abstract Scheduler getScheduler();

    public abstract void onEnable();
    public abstract void onDisable();

    public void registerCommand(ModuleCommand command) { }
    public void registerListener(Listener listener){ }
    public void registerEvent(Event event){ }
    public void callEvent(Event event){ }



}
