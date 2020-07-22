package de.crycodes.de.spacebyter.liptoncloud.time;

import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.utils.MillisUtils;

/**
 * Coded By CryCodes
 * Class: Counter
 * Date : 26.06.2020
 * Time : 17:33
 * Project: LiptonCloud
 */

public class Counter {

    private Long before;
    private Long after;

    public void start(){
        before = System.currentTimeMillis();
    }
    public void stop(){
        after = System.currentTimeMillis();
    }
    public void printResult(String actionName, CloudConsole colouredConsoleProvider){
        Long time = after - before;
        colouredConsoleProvider.getLogger().info("Action '" + actionName.toUpperCase() + "' took: (§a" + MillisUtils.getSeconds(time) + "." + time / 100 + "§r) Seconds!");
    }

}
