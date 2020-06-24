package de.crycodes.de.spacebyter.network.channel;

import de.crycodes.de.spacebyter.network.packet.Packet;

import java.util.ArrayList;

/*
 * Created by CryCodes on 25.03.2020
 * Project: CrazyCloud
 * Copyright: Nils Schrock | ERAPED.net
 */

public class Channel extends ArrayList<Object> {

    private static final long serialVersionUID = 8501296964229015349L;

    private String senderID = "UNSIGNED";
    private String senderGroupName = "UNSIGNED";

    public Channel(NetworkChannel networkChannel, Packet packet) {
        this.add(0, networkChannel.getChannelID());
        this.add(packet);
    }

    public Channel(String id, Object... o) {
        this.add(0, id);
        for (Object current : o) {
            this.add(current);
        }
    }

    public String id() {
        if (!(this.get(0) instanceof String)) {
            throw new IllegalArgumentException("Identifier of Packet is not a String");
        }
        return (String) this.get(0);
    }

    public String getSenderID() {
        return this.senderID;
    }

    public String getSenderGroup() {
        return this.senderGroupName;
    }

    public void sign(String senderID, String senderGroup) {
        this.senderID = senderID;
        this.senderGroupName = senderGroup;
    }

    @Deprecated
    public ArrayList<Object> open() {
        return this;
    }

}
