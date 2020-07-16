package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.util.Arrays;
import java.util.List;

public class MaintenanceUpdateHandler extends PacketHandlerAdapter {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="MaintenanceUpdateHandler">
    public MaintenanceUpdateHandler(LiptonMaster liptonMaster) {
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="handel">
    @Override
    public void handel(Packet packet) {
        if (packet instanceof UpdateMaintenancePacket){
            final UpdateMaintenancePacket maintenancePacket = (UpdateMaintenancePacket) packet;

            liptonMaster.getMasterConfig().getDocument().append("maintenance", maintenancePacket.isMaintenance());

            List<String> maintenancePlayers = Arrays.asList(liptonMaster.getMasterConfig().getDocument().getObject("whitelistedUser", String[].class));

            for (String addPlayer : maintenancePacket.getAddedPlayers()){
                if (!maintenancePlayers.contains(addPlayer))
                    maintenancePlayers.add(addPlayer);
            }
            for (String removedPlayer : maintenancePacket.getRemovedPlayers()){
                if (maintenancePlayers.contains(removedPlayer))
                    maintenancePlayers.remove(removedPlayer);
            }

            liptonMaster.getMasterConfig().getDocument().saveAsConfig(liptonMaster.getMasterConfig().getConfigFile());

        }
    }
    //</editor-fold>
}
