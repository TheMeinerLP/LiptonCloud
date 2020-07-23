package de.crycodes.de.spacebyter.network;

import de.crycodes.de.spacebyter.liptoncloud.enums.ExitState;
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

    public BackhandedClient(String hostname, int port, int timeout) {
        this(hostname, port, timeout, false, DEFAULT_USER_ID, DEFAULT_GROUP_ID);
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

    public void start() {
        stopped = false;
        login();
        startListening();
    }

    public void stop() {
        stopped = true;
    }

    protected void repairConnection() {
       System.exit(0);
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

            try {
                ObjectOutputStream out =
                        new ObjectOutputStream(new BufferedOutputStream(loginSocket.getOutputStream()));
                Channel loginPackage = new Channel("_INTERNAL_LOGIN_", id, group);
                loginPackage.sign(id, group);
                out.writeObject(loginPackage);
                out.flush();

            } catch (IOException ex) {
                System.exit(ExitState.TERMINATED.getState());
            }

        } catch (ConnectException e) {

            System.exit(ExitState.TERMINATED.getState());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(ExitState.TERMINATED.getState());
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
                        System.exit(ExitState.TERMINATED.getState());
                    } catch (ClassNotFoundException | IOException | InterruptedException ex) {
                        ex.printStackTrace();

                        System.exit(ExitState.TERMINATED.getState());
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
                try {

                    tempSocket.connect(address, timeout);
                } catch (Exception ignored) { }

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
        } catch (IOException | ClassNotFoundException ignored) { }

        return null;
    }

    public Channel sendMessage(Channel message) {
        return sendMessage(message, this.timeout);
    }

    public void registerMethod(String identifier, PacketRunner executable) {
        idMethods.put(identifier, executable);
    }

}
