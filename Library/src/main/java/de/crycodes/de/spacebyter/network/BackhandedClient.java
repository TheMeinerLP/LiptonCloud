package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.network.channel.Channel;
import de.crycodes.de.spacebyter.network.logger.MessageLogger;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.AlreadyConnectedException;
import java.util.HashMap;
import java.util.UUID;

/*
 * Created by CryCodes on 25.03.2020
 * Project: CrazyCloud
 * Copyright: Nils Schrock | ERAPED.net
 */

//SOME METHODS ARE NOT FROM NILS S.
public class BackhandedClient {

    protected String id;
    protected String group;

    protected Socket loginSocket;
    protected InetSocketAddress address;
    protected int timeout;

    protected Thread listeningThread;
    protected HashMap<String, PacketRunner> idMethods = new HashMap<String, PacketRunner>();

    protected int errorCount;

    protected boolean secureMode;
    protected boolean muted;
    protected boolean stopped;

    public static final String DEFAULT_USER_ID = UUID.randomUUID().toString();

    public static final String DEFAULT_GROUP_ID = "_DEFAULT_GROUP_";

    public BackhandedClient(String hostname, int port) {
        this(hostname, port, 10000, false, DEFAULT_USER_ID, DEFAULT_GROUP_ID);
    }

    public BackhandedClient(String hostname, int port, int timeout) {
        this(hostname, port, timeout, false, DEFAULT_USER_ID, DEFAULT_GROUP_ID);
    }

    public BackhandedClient(String hostname, int port, String id) {
        this(hostname, port, 10000, false, id, DEFAULT_GROUP_ID);
    }

    public BackhandedClient(String hostname, int port, String id, String group) {
        this(hostname, port, 10000, false, id, group);
    }

    public BackhandedClient(String hostname, int port, int timeout, boolean useSSL, String id, String group) {
        this.id = id;
        this.group = group;

        this.errorCount = 0;
        this.address = new InetSocketAddress(hostname, port);
        this.timeout = timeout;

        this.secureMode = useSSL;
        if (secureMode) {
            System.setProperty("javax.net.ssl.trustStore", "ssc.store");
            System.setProperty("javax.net.ssl.keyStorePassword", "SimpleServerClient");
        }
    }
    public BackhandedClient(String hostname, int port, int timeout, boolean useSSL) {

        this.errorCount = 0;
        this.address = new InetSocketAddress(hostname, port);
        this.timeout = timeout;

        this.secureMode = useSSL;
        if (secureMode) {
            System.setProperty("javax.net.ssl.trustStore", "ssc.store");
            System.setProperty("javax.net.ssl.keyStorePassword", "SimpleServerClient");
        }
    }

    public boolean isListening() {
        return isConnected() && listeningThread != null && listeningThread.isAlive() && errorCount == 0;
    }

    public boolean isConnected() {
        return loginSocket != null && loginSocket.isConnected();
    }

    public boolean isServerReachable() {
        try {
            Socket tempSocket = new Socket();
            tempSocket.connect(this.address);
            tempSocket.isConnected();
            tempSocket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void start() {
        stopped = false;
        login();
        startListening();
    }

    public void stop() {
        stopped = true;
        onLog("[Client] Stopping...");
    }

    protected void repairConnection() {
       System.exit(0);
        /* if (loginSocket != null) {
            try {
                loginSocket.close();
            } catch (IOException e) {
            }
            loginSocket = null;
        }
        login();
        startListening();*/
    }

    protected void login() {
        if (stopped) {
            return;
        }

        try {
            if (loginSocket != null && loginSocket.isConnected()) {
                throw new AlreadyConnectedException();
            }

            if (secureMode) {
                loginSocket =
                        SSLSocketFactory.getDefault().createSocket(address.getAddress(), address.getPort());
            } else {
                loginSocket = new Socket();
                loginSocket.connect(this.address, this.timeout);
            }

            onLog("[Client] Connected to " + loginSocket.getRemoteSocketAddress());

            try {
                ObjectOutputStream out =
                        new ObjectOutputStream(new BufferedOutputStream(loginSocket.getOutputStream()));
                Channel loginPackage = new Channel("_INTERNAL_LOGIN_", id, group);
                loginPackage.sign(id, group);
                out.writeObject(loginPackage);
                out.flush();

                onReconnect();
            } catch (IOException ex) {
                onLogError("[Client] Login failed.");
            }

        } catch (ConnectException e) {
            onLogError("[Client] Connection failed: " + e.getMessage());
            onConnectionProblem();
        } catch (IOException e) {
            e.printStackTrace();
            onConnectionProblem();
        }
    }

    protected void startListening() {

        if (listeningThread != null && listeningThread.isAlive()) {
            return;
        }

        listeningThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!stopped) {
                    try {
                        // repait connection if something went wrong with the connection
                        if (loginSocket != null && !loginSocket.isConnected()) {
                            while (!loginSocket.isConnected()) {
                                repairConnection();
                                if (loginSocket.isConnected()) {
                                    break;
                                }

                                Thread.sleep(5000);
                                repairConnection();
                            }
                        }

                        onConnectionGood();

                        ObjectInputStream ois =
                                new ObjectInputStream(new BufferedInputStream(loginSocket.getInputStream()));
                        Object raw = ois.readObject();

                        if (stopped) {
                            return;
                        }

                        if (raw instanceof Channel) {
                            final Channel msg = (Channel) raw;
                            for (final String current : idMethods.keySet()) {
                                if (current.equalsIgnoreCase(msg.id())) {
                                    onLog("NEW Channel: " + msg.id());
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            idMethods.get(current).run(msg, loginSocket);
                                        }
                                    }).start();
                                    break;
                                }
                            }

                        }

                    } catch (SocketException e) {
                        onConnectionProblem();
                        onLogError("[Client] Connection lost");
                        repairConnection();
                    } catch (ClassNotFoundException | IOException | InterruptedException ex) {
                        ex.printStackTrace();
                        onConnectionProblem();
                        onLogError("[Client] Error: The connection to the server is currently interrupted!");
                        repairConnection();
                    }

                    errorCount = 0;

                }

            }
        });

        listeningThread.start();
    }

    public Channel sendMessage(Channel message, int timeout) {
        try {
            // connect to the target client's socket
            Socket tempSocket;
            if (secureMode) {
                tempSocket =
                        SSLSocketFactory.getDefault().createSocket(address.getAddress(), address.getPort());
            } else {
                tempSocket = new Socket();
                tempSocket.connect(address, timeout);
            }

            ObjectOutputStream tempOOS =
                    new ObjectOutputStream(new BufferedOutputStream(tempSocket.getOutputStream()));
            message.sign(id, group);
            tempOOS.writeObject(message);
            tempOOS.flush();

            ObjectInputStream tempOIS = new ObjectInputStream(new BufferedInputStream(tempSocket.getInputStream()));
            Object raw = tempOIS.readObject();

            tempOOS.close();
            tempOIS.close();
            tempSocket.close();

            if (raw instanceof Channel) {
                return (Channel) raw;
            }
        } catch (EOFException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            onLogError("[Client] Error while sending message");
            ex.printStackTrace();
        }

        return null;
    }

    public Channel sendMessage(String ID, Object... content) {
        return sendMessage(new Channel(ID, content));
    }

    public Channel sendMessage(Channel message) {
        return sendMessage(message, this.timeout);
    }

    public void registerMethod(String identifier, PacketRunner executable) {
        idMethods.put(identifier, executable);
    }

    public void onConnectionProblem() {
    }

    public void onConnectionGood() {
    }

    public void onReconnect() {
    }

    public void onLog(String message) {
        MessageLogger.getGlobalLogger().info(message);
    }

    public void onLogError(String message) {
        MessageLogger.getGlobalLogger().error(message);
    }
}
