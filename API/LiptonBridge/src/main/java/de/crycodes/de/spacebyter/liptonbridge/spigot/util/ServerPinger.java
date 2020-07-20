package de.crycodes.de.spacebyter.liptonbridge.spigot.util;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Coded By CryCodes
 * Class: ServerPinger
 * Date : 20.07.2020
 * Time : 16:04
 * Project: LiptonCloud
 */

public class ServerPinger {

    private String motd;
    private int players;
    private int maxplayers;
    private boolean online;

    private Socket socket = null;
    private OutputStream outputStream = null;
    private DataOutputStream dataOutputStream = null;
    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;

    public void pingServer(final String adress, final int port, final int timeout) throws IOException {
        socket = new Socket();
        socket.setSoTimeout(timeout);
        socket.connect(new InetSocketAddress(adress, port), timeout);

        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
        inputStream = socket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
        dataOutputStream.write(new byte[] { -2, 1 });

        int packetId = inputStream.read();
        if(packetId == -1 || packetId !=  255)
            close();

        int length = inputStreamReader.read();
        if (length == -1 || length == 0)
            close();

        final char[] chars = new char[length];

        if (inputStreamReader.read(chars, 0, length) != length)
            close();

        final String string = new String(chars);

        if (string.startsWith("§")) {
            final String[] data = string.split("\u0000");
            motd = data[3];
            players = Integer.parseInt(data[4]);
            maxplayers = Integer.parseInt(data[5]);
            online = true;
            close();
        } else {
            final String[] data = string.split("§");
            motd = data[0];
            players = Integer.parseInt(data[1]);
            maxplayers = Integer.parseInt(data[2]);
            online = true;
            close();
        }
    }

    //<editor-fold desc="close">
    private void close() throws IOException {
        dataOutputStream.close();
        outputStream.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();
    }
    //</editor-fold>


    //<editor-fold desc="setMotd">
    public void setMotd(String motd) {
        this.motd = motd;
    }
    //</editor-fold>

    //<editor-fold desc="setPlayers">
    public void setPlayers(int players) {
        this.players = players;
    }
    //</editor-fold>

    //<editor-fold desc="setMaxplayers">
    public void setMaxplayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }
    //</editor-fold>

    //<editor-fold desc="setOnline">
    public void setOnline(boolean online) {
        this.online = online;
    }
    //</editor-fold>

    //<editor-fold desc="getMotd">
    public String getMotd() {
        return motd;
    }
    //</editor-fold>

    //<editor-fold desc="getPlayers">
    public int getPlayers() {
        return players;
    }
    //</editor-fold>

    //<editor-fold desc="getMaxplayers">
    public int getMaxplayers() {
        return maxplayers;
    }
    //</editor-fold>

    //<editor-fold desc="isOnline">
    public boolean isOnline() {
        return online;
    }
    //</editor-fold>
}
