package de.crycodes.de.spacebyter.liptoncloud.addon;

import de.crycodes.de.spacebyter.liptoncloud.addon.extendable.AddonExtendable;

import java.io.Serializable;
import java.util.Queue;

public abstract class AddonLoader extends AddonExtendable implements Serializable {

    /**
     * Disabled a loaded addon
     *
     * @param arg1 The name of the addon
     * @return If the addon could be disabled or not
     */
    public abstract boolean disableAddon(String arg1);

    /**
     * Enabled a unloaded addon
     *
     * @param arg1 The name of the addon
     * @return If the addon could be enabled or not
     */
    public abstract boolean enableAddon(String arg1);

    /**
     * Loads an addon
     *
     * @param arg1 The name of the addon
     * @return If the addon could be loaded or not
     */
    public abstract boolean isAddonEnabled(String arg1);

    /**
     * Get all enabled addons
     *
     * @return A queue containing all loaded addons
     */
    public abstract Queue<JavaAddon> getJavaAddons();
}
