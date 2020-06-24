package de.crycodes.de.spacebyter.liptoncloud.addon.extendable;


import de.crycodes.de.spacebyter.liptoncloud.addon.JavaAddon;

public interface AddonPreLoader {

    /**
     * Loads the Addon main class and creates a new instance
     *
     * @return The loaded java addon
     * @throws Throwable The exception will be thrown if any error occurs while loading the addon
     */
    JavaAddon loadAddon() throws Throwable;
}
