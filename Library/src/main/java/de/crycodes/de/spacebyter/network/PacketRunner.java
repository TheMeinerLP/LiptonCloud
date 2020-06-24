package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.network.channel.Channel;

import java.net.Socket;

/*
 * Created by CryCodes on 25.03.2020
 * Project: CrazyCloud
 * Copyright: Nils Schrock | ERAPED.net
 */

public interface PacketRunner {

    /**
     * Implement this method using
     * registerMethod(String identifier, PacketRunner executable) of a Server
     * or Client to handle incoming Datapackages. Server only: If you send a reply
     * to a client from an implementation of this method, use
     * sendReply(Socket toSocket, Object... datapackageContent).
     *
     * @param pack The Packet received
     * @param socket The Socket you received the Packet from
     */
    public abstract void run(Channel pack, Socket socket);

}
