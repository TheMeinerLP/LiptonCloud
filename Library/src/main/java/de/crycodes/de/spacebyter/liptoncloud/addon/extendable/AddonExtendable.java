package de.crycodes.de.spacebyter.liptoncloud.addon.extendable;

public abstract class AddonExtendable {

    /**
     * This method will load the addons
     */
    public abstract void loadAddons();

    /**
     * This method will enable the addons
     */
    public abstract void enableAddons();

    /**
     * This method disables the addons
     */
    public abstract void disableAddons();
}
