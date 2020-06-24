package de.crycodes.de.spacebyter.liptoncloud;

import de.crycodes.de.spacebyter.liptoncloud.addon.AddonParallelLoader;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.event.EventManager;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.AsciiPrinter;

/**
 * Coded By CryCodes
 * Class: LiptonLibrary
 * Date : 24.06.2020
 * Time : 10:25
 * Project: LiptonCloud
 */

public class LiptonLibrary {

    public LiptonLibrary(Scheduler scheduler, EventManager eventManager, ColouredConsoleProvider colouredConsoleProvider, AddonParallelLoader parallelLoader,  Boolean useColor) {

        new AsciiPrinter().Print(colouredConsoleProvider, useColor);
    }
}
