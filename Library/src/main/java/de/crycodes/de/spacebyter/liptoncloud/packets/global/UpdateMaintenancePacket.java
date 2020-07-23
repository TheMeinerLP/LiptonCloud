package de.crycodes.de.spacebyter.liptoncloud.packets.global;

import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

import java.util.List;

public class UpdateMaintenancePacket extends Packet {

    private final List<String> addedPlayers;
    private final List<String> removedPlayers;
    private final boolean maintenance;

    public UpdateMaintenancePacket(List<String> addedPlayers, List<String> removedPlayers, boolean maintenance, ReceiverType receiverType) {
        super("UpdateMaintenancePacket", receiverType);
        this.addedPlayers = addedPlayers;
        this.removedPlayers = removedPlayers;
        this.maintenance = maintenance;
    }

    public List<String> getAddedPlayers() {
        return addedPlayers;
    }

    public List<String> getRemovedPlayers() {
        return removedPlayers;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

}
