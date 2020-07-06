package de.crycodes.de.spacebyter.liptonbridge.spigot.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 18:41                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class Pinger {

    public static String motd;
    public static int players;
    public static int maxplayers;
    public static boolean Online;

    static {
        Pinger.motd = null;
        Pinger.players = 0;
        Pinger.maxplayers = 0;
        Pinger.Online = false;
    }

    public String getMotd() {
        return Pinger.motd;
    }

    public int getPlayerCount() {
        return Pinger.players;
    }

    public int getMaxPlayerCount() {
        return Pinger.maxplayers;
    }

    public boolean isOnline() {
        return Pinger.Online;
    }

    public void pingServer(final String adress, final int port, final int timeout) {
        try {
            final Socket socket = new Socket();
            socket.setSoTimeout(timeout);
            socket.connect(new InetSocketAddress(adress, port), timeout);
            final OutputStream outputStream = socket.getOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            final InputStream inputStream = socket.getInputStream();
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
            dataOutputStream.write(new byte[] { -2, 1 });
            final int packetId = inputStream.read();
            if (packetId == -1) {
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("ERROR");
            }
            if (packetId != 255) {
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("ERROR");
            }
            final int length = inputStreamReader.read();
            if (length == -1) {
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("ERROR");
            }
            if (length == 0) {
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("ERROR");
            }
            final char[] chars = new char[length];
            if (inputStreamReader.read(chars, 0, length) != length) {
                dataOutputStream.close();
                outputStream.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                throw new IOException("ERROR");
            }
            final String string = new String(chars);
            if (string.startsWith("§")) {
                final String[] data = string.split("\u0000");
                Pinger.motd = data[3];
                Pinger.players = Integer.parseInt(data[4]);
                Pinger.maxplayers = Integer.parseInt(data[5]);
                Pinger.Online = true;
                socket.close();
            }
            else {
                final String[] data = string.split("§");
                Pinger.motd = data[0];
                Pinger.players = Integer.parseInt(data[1]);
                Pinger.maxplayers = Integer.parseInt(data[2]);
                Pinger.Online = true;
                socket.close();
            }
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
        }
        catch (SocketException exception) {
            Pinger.Online = false;
        }
        catch (IOException exception2) {
            Pinger.Online = false;
        }
    }
}

