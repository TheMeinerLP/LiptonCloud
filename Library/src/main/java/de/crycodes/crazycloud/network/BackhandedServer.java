package de.crycodes.crazycloud.network;

import de.crycodes.crazycloud.network.channel.Channel;
import de.crycodes.crazycloud.network.logger.MessageLogger;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/*
 * Created by CryCodes on 25.03.2020
 * Project: CrazyCloud
 * Copyright: Nils Schrock | ERAPED.net
 */

//SOME METHODS ARE NOT FROM NILS S.
public abstract class BackhandedServer {

    protected HashMap<String, PacketRunner> idMethods = new HashMap<String, PacketRunner>();

    protected ServerSocket server;
    protected int port;
    protected ArrayList<RemoteClient> clients;
    protected ArrayList<RemoteClient> toBeDeleted;

    protected Thread listeningThread;

    protected boolean autoRegisterEveryClient;
    protected boolean secureMode;

    protected boolean stopped;
    protected boolean muted;
    protected long pingInterval = 30 * 1000; // 30 seconds

    protected static final String INTERNAL_LOGIN_ID = "_INTERNAL_LOGIN_";

    @Deprecated
    public BackhandedServer(int port) {
        this(port, true, true, false);
    }

    public BackhandedServer(int port, boolean muted) {
        this(port, true, true, false, muted);
    }

    @Deprecated
    public BackhandedServer(int port, boolean autoRegisterEveryClient, boolean keepConnectionAlive, boolean useSSL) {
        this.clients = new ArrayList<RemoteClient>();
        this.port = port;
        this.autoRegisterEveryClient = autoRegisterEveryClient;
        this.muted = false;

        this.secureMode = useSSL;
        if (secureMode) {
            System.setProperty("javax.net.ssl.keyStore", "ssc.store");
            System.setProperty("javax.net.ssl.keyStorePassword", "SimpleServerClient");
        }
        if (autoRegisterEveryClient) {
            registerLoginMethod();
        }
        preStart();

        start();

        if (keepConnectionAlive) {
            startPingThread();
        }
    }


    public BackhandedServer(int port, boolean autoRegisterEveryClient, boolean keepConnectionAlive, boolean useSSL, boolean muted) {
        this.clients = new ArrayList<RemoteClient>();
        this.port = port;
        this.autoRegisterEveryClient = autoRegisterEveryClient;
        this.muted = muted;

        this.secureMode = useSSL;
        if (secureMode) {
            System.setProperty("javax.net.ssl.keyStore", "ssc.store");
            System.setProperty("javax.net.ssl.keyStorePassword", "SimpleServerClient");
        }
        if (autoRegisterEveryClient) {
            registerLoginMethod();
        }
        preStart();

        start();

        if (keepConnectionAlive) {
            startPingThread();
        }
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setPingInterval(int seconds) {
        this.pingInterval = seconds * 1000;
    }

    protected void startPingThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (server != null) {
                    try {
                        Thread.sleep(pingInterval);
                    } catch (InterruptedException e) {
                    }
                    broadcastMessage(new Channel("_INTERNAL_PING_", "OK"));
                }

            }
        }).start();
    }

    protected void startListening() {
        if (listeningThread == null && server != null) {
            listeningThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (!Thread.interrupted() && !stopped && server != null) {

                        try {
                            final Socket tempSocket = server.accept();

                            ObjectInputStream ois =
                                    new ObjectInputStream(new BufferedInputStream(tempSocket.getInputStream()));
                            Object raw = ois.readObject();

                            if (raw instanceof Channel) {
                                final Channel msg = (Channel) raw;
                                for (final String current : idMethods.keySet()) {
                                    if (msg.id().equalsIgnoreCase(current)) {
                                        if (msg.id().equalsIgnoreCase("_INTERNAL_LOGIN_")){
                                           onLog("Client connected from " + server.getInetAddress().getHostName() + ".");
                                        } else {
                                        }

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                idMethods.get(current).run(msg, tempSocket);
                                                if (!msg.id().equals(INTERNAL_LOGIN_ID)) {
                                                    try {
                                                        tempSocket.close();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }).start();
                                        break;
                                    }
                                }

                            }

                        } catch (SocketException e) {
                            onLog("Server stopped.");
                        } catch (IllegalBlockingModeException | IOException | ClassNotFoundException e) {
                        }

                    }
                }

            });

            listeningThread.start();
        }
    }

    public synchronized void sendReply(Socket toSocket, Object... datapackageContent) {
        sendMessage(new RemoteClient(null, toSocket), new Channel("REPLY", datapackageContent));
    }

    public synchronized void sendMessage(String remoteClientId, String datapackageId,
                                         Object... datapackageContent) {
        sendMessage(remoteClientId, new Channel(datapackageId, datapackageContent));
    }

    public synchronized void sendMessage(String remoteClientId, Channel message) {
        for (RemoteClient current : clients) {
            if (current.getId().equals(remoteClientId)) {
                sendMessage(current, message);
            }
        }
    }

    public synchronized void sendMessage(RemoteClient remoteClient, String datapackageId,
                                         Object... datapackageContent) {
        sendMessage(remoteClient, new Channel(datapackageId, datapackageContent));
    }

    public synchronized void sendMessage(RemoteClient remoteClient, Channel message) {
        try {
            if (!remoteClient.getSocket().isConnected()) {
                throw new ConnectException("Socket not connected.");
            }
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(remoteClient.getSocket().getOutputStream()));
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            onLogError("[Server] [Send Message] Error: " + e.getMessage());

            if (toBeDeleted != null) {
                toBeDeleted.add(remoteClient);
            } else {
                clients.remove(remoteClient);
                onClientRemoved(remoteClient);
            }
        }
    }

    public synchronized int broadcastMessageToGroup(String group, Channel message) {
        toBeDeleted = new ArrayList<RemoteClient>();

        // send message to all clients
        int txCounter = 0;
        for (RemoteClient current : clients) {
            if (current.getGroup().equals(group)) {
                sendMessage(current, message);
                txCounter++;
            }
        }

        txCounter -= toBeDeleted.size();
        for (RemoteClient current : toBeDeleted) {
            clients.remove(current);
            onClientRemoved(current);
        }

        toBeDeleted = null;

        return txCounter;
    }

    public synchronized int broadcastMessage(Channel message) {
        toBeDeleted = new ArrayList<RemoteClient>();

        int txCounter = 0;
        for (RemoteClient current : clients) {
            sendMessage(current, message);
            txCounter++;
        }

        txCounter -= toBeDeleted.size();
        for (RemoteClient current : toBeDeleted) {
            clients.remove(current);
            onClientRemoved(current);
        }

        toBeDeleted = null;

        return txCounter;
    }

    public void registerMethod(String identifier, PacketRunner executable) {
        if (identifier.equalsIgnoreCase(INTERNAL_LOGIN_ID) && autoRegisterEveryClient) {
            throw new IllegalArgumentException("Identifier may not be '" + INTERNAL_LOGIN_ID + "'. "
                    + "Since v1.0.1 the server automatically registers new clients. "
                    + "To react on new client registed, use the onClientRegisters() listener by overwriting it.");
        }
        idMethods.put(identifier, executable);
    }

    protected void registerLoginMethod() {
        idMethods.put(INTERNAL_LOGIN_ID, new PacketRunner() {
            @Override
            public void run(Channel msg, Socket socket) {
                if (msg.size() == 3) {
                    registerClient((String) msg.get(1), (String) msg.get(2), socket);
                } else if (msg.size() == 2) {
                    registerClient((String) msg.get(1), socket);
                } else {
                    registerClient(UUID.randomUUID().toString(), socket);
                }
                onClientRegistered(msg, socket);
                onClientRegistered();
            }
        });
    }

    protected synchronized void registerClient(String id, Socket newClientSocket) {
        clients.add(new RemoteClient(id, newClientSocket));
    }

    protected synchronized void registerClient(String id, String group, Socket newClientSocket) {
        clients.add(new RemoteClient(id, group, newClientSocket));
    }

    protected void start() {
        stopped = false;
        server = null;
        try {

            if (secureMode) {
                server = SSLServerSocketFactory.getDefault().createServerSocket(port);
            } else {
                server = new ServerSocket(port);
            }

        } catch (IOException e) {

        }
        startListening();
    }

    public void stop() throws IOException {
        stopped = true;

        if (listeningThread.isAlive()) {
            listeningThread.interrupt();
        }

        if (server != null) {
            server.close();
        }
    }

    public synchronized int getClientCount() {
        return clients != null ? clients.size() : 0;
    }

    public boolean isClientIdConnected(String clientId) {
        if (clients != null && clients.size() > 0) {
            // Iterate all clients connected
            for (RemoteClient c : clients) {
                // Check client exists and its socket is connected
                if (c.getId().equals(clientId) && c.getSocket() != null && c.getSocket().isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAnyClientConnected() {
        return getClientCount() > 0;
    }

    public abstract void preStart();

    public void onClientRegistered() {
    }

    public void onClientRegistered(Channel msg, Socket socket) {
    }

    public void onClientRemoved(RemoteClient remoteClient) {
    }

    public void onServerStopped() {
    }

    public void onLog(String message) {
        MessageLogger.getGlobalLogger().info(message);
    }

    public void onLogError(String message) {
        MessageLogger.getGlobalLogger().error(message);
    }

    protected class RemoteClient {
        private String id;
        private String group;
        private Socket socket;

        public RemoteClient(String id, Socket socket) {
            this.id = id;
            this.group = "_DEFAULT_GROUP_";
            this.socket = socket;
        }

        public RemoteClient(String id, String group, Socket socket) {
            this.id = id;
            this.group = group;
            this.socket = socket;
        }

        public String getId() {
            return id;
        }

        public String getGroup() {
            return group;
        }

        public Socket getSocket() {
            return socket;
        }

        @Override
        public String toString() {
            return "[RemoteClient: " + id + " (" + group + ") @ " + socket.getRemoteSocketAddress() + "]";
        }
    }

}
