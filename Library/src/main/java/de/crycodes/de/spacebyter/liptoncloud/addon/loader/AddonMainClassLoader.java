package de.crycodes.de.spacebyter.liptoncloud.addon.loader;

import de.crycodes.de.spacebyter.liptoncloud.addon.JavaAddon;
import de.crycodes.de.spacebyter.liptoncloud.addon.configuration.AddonClassConfig;
import de.crycodes.de.spacebyter.liptoncloud.addon.extendable.AddonPreLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class AddonMainClassLoader extends URLClassLoader implements AddonPreLoader {

    /**
     * The class config of the addon
     */
    private final AddonClassConfig addonClassConfig;

    /**
     * Loads the Addon Main Class
     *
     * @param addonClassConfig The class config of the addon
     * @throws MalformedURLException The exception will be thrown if the config cannot be found
     */
    public AddonMainClassLoader(final AddonClassConfig addonClassConfig)
        throws MalformedURLException {
        super(new URL[]{addonClassConfig.getFile().toURI().toURL()});
        this.addonClassConfig = addonClassConfig;
    }

    @Override
    public JavaAddon loadAddon() throws Throwable {
        JavaAddon javaAddon = (JavaAddon) loadClass(addonClassConfig.getMain())
            .getDeclaredConstructor().newInstance();

        javaAddon.setAddonClassConfig(this.addonClassConfig);
        javaAddon.onPrepare();

        return javaAddon;
    }
}
