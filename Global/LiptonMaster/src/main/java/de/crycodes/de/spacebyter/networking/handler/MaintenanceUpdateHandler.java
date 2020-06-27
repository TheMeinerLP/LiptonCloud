package de.crycodes.de.spacebyter.networking.handler;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.network.adapter.PacketHandlerAdapter;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.util.Arrays;
import java.util.List;

public class MaintenanceUpdateHandler extends PacketHandlerAdapter {

    @Override
    public void handel(Packet packet) {
        if (packet instanceof UpdateMaintenancePacket){
            final UpdateMaintenancePacket maintenancePacket = (UpdateMaintenancePacket) packet;

            LiptonMaster.getInstance().getMasterConfig().getDocument().append("maintenance", maintenancePacket.isMaintenance());

            List<String> maintenancePlayers = Arrays.asList(LiptonMaster.getInstance().getMasterConfig().getDocument().getObject("whitelistedUser", String[].class));

            for (String addPlayer : maintenancePacket.getAddedPlayers()){
                if (!maintenancePlayers.contains(addPlayer))
                    maintenancePlayers.add(addPlayer);
            }
            for (String removedPlayer : maintenancePacket.getRemovedPlayers()){
                if (maintenancePlayers.contains(removedPlayer))
                    maintenancePlayers.remove(removedPlayer);
            }

            LiptonMaster.getInstance().getMasterConfig().getDocument().saveAsConfig(LiptonMaster.getInstance().getMasterConfig().getConfigFile());

        }
    }
}
