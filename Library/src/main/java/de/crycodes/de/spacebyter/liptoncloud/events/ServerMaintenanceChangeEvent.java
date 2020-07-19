package de.crycodes.de.spacebyter.liptoncloud.events;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;

/**
 * Coded By ErazeYT
 * Class: ServerMaintenanceChangeEvent
 * Date : 19.07.2020
 * Time : 23:25
 * Project: LiptonCloud3
 */

public class ServerMaintenanceChangeEvent extends Event {

    private final Boolean isMaintenance;

    public ServerMaintenanceChangeEvent(Boolean isMaintenance) {
        this.isMaintenance = isMaintenance;
    }

    public Boolean isMaintenance() {
        return isMaintenance;
    }
}
