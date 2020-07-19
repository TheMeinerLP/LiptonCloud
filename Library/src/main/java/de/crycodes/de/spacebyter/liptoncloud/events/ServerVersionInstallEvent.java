package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.versions.SpigotVersions;

/**
 * Coded By ErazeYT
 * Class: ServerVersionInstallEvent
 * Date : 19.07.2020
 * Time : 23:51
 * Project: LiptonCloud3
 */

public class ServerVersionInstallEvent extends Event {

    private final SpigotVersions spigotVersion;

    public ServerVersionInstallEvent(SpigotVersions spigotVersion) {
        this.spigotVersion = spigotVersion;
    }

    public SpigotVersions getSpigotVersion() {
        return spigotVersion;
    }
}
