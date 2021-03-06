package de.crycodes.de.spacebyter.screen;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Coded By CryCodes
 * Class: ScreenManager
 * Date : 16.07.2020
 * Time : 17:25
 * Project: LiptonCloud
 */

public class ScreenManager {

    private ConcurrentHashMap<String, Screen> screenConcurrentHashMap;

    //<editor-fold desc="ScreenManager">
    public ScreenManager() {
        this.screenConcurrentHashMap = new ConcurrentHashMap<>();
    }
    //</editor-fold>

    //<editor-fold desc="register - getter">
    public Screen getScreenByName(String name){
        return screenConcurrentHashMap.getOrDefault(name, null);
    }
    public void unregisterScreen(Screen screen) {
        this.screenConcurrentHashMap.remove(screen.getName());
    }
    public void registerScreen(Screen screen, String name){
        this.screenConcurrentHashMap.put(name,screen);
    }

    public ConcurrentHashMap<String, Screen> getScreenConcurrentHashMap() {
        return screenConcurrentHashMap;
    }

    //</editor-fold>
}
