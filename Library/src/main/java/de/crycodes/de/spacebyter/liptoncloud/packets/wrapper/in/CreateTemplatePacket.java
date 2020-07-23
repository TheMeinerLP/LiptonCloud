package de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in;

import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.network.packet.Packet;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

/**
 * Coded By CryCodes
 * Class: CreateTemplate
 * Date : 26.06.2020
 * Time : 08:37
 * Project: LiptonCloud
 */

public class CreateTemplatePacket extends Packet {

    private final String templateName;
    private final String wrapperID;
    private final ServerGroupMeta serverGroupMeta;

    public CreateTemplatePacket(String templateName, String wrapperID, ServerGroupMeta serverGroupMeta, ReceiverType receiverType) {
        super("CreateTemplatePacket", receiverType);
        this.templateName = templateName;
        this.wrapperID = wrapperID;
        this.serverGroupMeta = serverGroupMeta;
    }

    public ServerGroupMeta getServerGroupMeta() {
        return serverGroupMeta;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getWrapperID() {
        return wrapperID;
    }
}
