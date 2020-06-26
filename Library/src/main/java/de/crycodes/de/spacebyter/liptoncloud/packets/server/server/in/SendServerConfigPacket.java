package de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in;

import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.network.packet.Packet;

/**
 * Coded By CryCodes
 * Class: SendServerConfig
 * Date : 26.06.2020
 * Time : 08:48
 * Project: LiptonCloud
 */

public class SendServerConfigPacket extends Packet {

    private final ServerConfig serverConfig;
    private final String serverName;

    public SendServerConfigPacket(ServerConfig serverConfig, String serverName) {
        super("SendServerConfigPacket");
        this.serverConfig = serverConfig;
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }
}
