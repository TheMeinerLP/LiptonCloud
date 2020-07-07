package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.ServerGroupConfig;
import de.crycodes.de.spacebyter.liptoncloud.exceprtion.ServerIDNotFoundException;
import de.crycodes.de.spacebyter.liptoncloud.exceprtion.ServerNotStartedException;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Coded By CryCodes
 * Class: ServerManager
 * Date : 24.06.2020
 * Time : 19:59
 * Project: LiptonCloud
 */

public class ServerManager {

    private final LiptonMaster liptonMaster;
    private final ServerGroupConfig serverGroupConfig;

    private final ConcurrentHashMap<String, ServerMeta> globalServerList;
    private final ConcurrentHashMap<String, ServerMeta> startedServer;

    private int tick;

    //<editor-fold desc="ServerManager">
    public ServerManager(LiptonMaster liptonMaster, ServerGroupConfig serverGroupConfig) {
        globalServerList = new ConcurrentHashMap<>();
        startedServer = new ConcurrentHashMap<>();

        this.serverGroupConfig = serverGroupConfig;
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="Start - Stop Logic">
    @ShouldRunAsync
    public void start(){
        tick = liptonMaster.getScheduler().scheduleAsyncWhile(new Runnable() {
            @Override
            public void run() {
                List<ServerGroupMeta> availableServerGroups = serverGroupConfig.getServerMetas();

                if (liptonMaster.getWrapperManager().getBestFreeWrapper() == null) return;

                availableServerGroups.forEach(serverGroupMeta -> {

                    final WrapperMeta wrapperMeta = liptonMaster.getWrapperManager().getBestFreeWrapper();
                    final String bestWrapperID = wrapperMeta.getWrapperConfig().getWrapperId();


                    if (getOnlineServerByGroup(LiptonMaster.getInstance().getServerGroupConfig().getServerMetaByName("Lobby")) == 0 &&
                            getStartedServerByGroup(LiptonMaster.getInstance().getServerGroupConfig().getServerMetaByName("Lobby")) == 0 ){

                        startServer(LiptonMaster.getInstance().getServerGroupConfig().getServerMetaByName("Lobby"), bestWrapperID, true);
                        return;
                    }

                    int minServer = serverGroupMeta.getMinServer();




                    if (getOnlineServerByGroup(serverGroupMeta) + getStartedServerByGroup(serverGroupMeta) < minServer)
                        startServer(serverGroupMeta, bestWrapperID, true);


                });
            }
        }, 2500, 2500);
    }
    public void stop(){
        liptonMaster.getScheduler().cancelTask(this.tick);
    }
    //</editor-fold>

    //<editor-fold desc="StartServer Method">
    public void startServer(@ShouldNotBeNull ServerGroupMeta serverGroupMeta, String wrapperID, boolean autoStart){
        if (getStartedServerByGroup(serverGroupMeta) >= serverGroupMeta.getMaxMemory()) {
            liptonMaster.getColouredConsoleProvider().error("To many Server's are running of group " + serverGroupMeta.getGroupName());
            return;
        }
        int port = liptonMaster.getPortManager().getFreePort();
        int id = liptonMaster.getIdManager().getFreeID(serverGroupMeta.getGroupName());
        final ServerMeta serverMeta = new ServerMeta(serverGroupMeta.getGroupName() + liptonMaster.getMasterConfig().getServerNameSplitter() + id ,id, serverGroupMeta, wrapperID, "127.0.0.1", port);
        this.startedServer.put(serverMeta.getServerName().toUpperCase() ,serverMeta);

        liptonMaster.getMasterProxyServer().getServer().sendPacket(
                liptonMaster.getMasterProxyServer().getNetworkChannel(),
                new StartServerPacket(wrapperID ,serverMeta));

        LiptonMaster.getInstance().getMasterWrapperServer().sendPacket(new StartServerPacket(wrapperID, serverMeta));

        if (!autoStart) this.liptonMaster.getColouredConsoleProvider().info("StartetServer: " + serverMeta.getServerName() + " | on port: " + serverMeta.getPort() + " | on wrapper: " + serverMeta.getWrapperID() + " | group: " + serverGroupMeta.getGroupName() + " !");
        if (autoStart)  liptonMaster.getColouredConsoleProvider().info("§c[AUTOSTART]§r StartetServer '" + serverGroupMeta.getGroupName() + "' stats: (" + getGlobalStartedAndOnlineServerByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ") Port: §a" + port + "§r | ID: §a" + id + "§r !");
    }
    //</editor-fold>

    //<editor-fold desc="Register Method">
    public void registerServer(String serverName){
        final String name = serverName.toUpperCase();

        if (name.equalsIgnoreCase("NONE")){
            liptonMaster.getColouredConsoleProvider().error("Dummy-Server tried to register");
            return;
        }


        if (!this.startedServer.containsKey(name)){
            liptonMaster.getColouredConsoleProvider().error("Server with name: '" + serverName + "' was not found in StartServer-List!");
            try {
                throw new ServerNotStartedException("Server: " + name + " was not found");
            } catch (ServerNotStartedException e) {
                e.printStackTrace();
            }
            return;
        }

        final ServerMeta serverMeta = this.startedServer.get(name);
        this.startedServer.remove(name);

        this.globalServerList.put(serverMeta.getServerName().toUpperCase(), serverMeta);
        liptonMaster.getColouredConsoleProvider().info("Registered new Spigot Server: (§c" + serverName + "§r)!");

    }
    //</editor-fold>

    //<editor-fold desc="UnRegister Method">
    public void unregisterServer(String serverName){
        final String name = serverName.toUpperCase();
        if (this.globalServerList.containsKey(name)){
            final ServerMeta serverMeta = globalServerList.get(name);

                if (liptonMaster.getIdManager().getServerIdList().get(serverMeta.getServerGroupMeta().getGroupName()).contains(serverMeta.getId())){
                    liptonMaster.getIdManager().removeID(serverMeta.getServerGroupMeta().getGroupName(),serverMeta.getId());
                } else {
                    try {
                        throw new ServerIDNotFoundException("Server id for server: '" + serverName + "' was mot found");
                    } catch (ServerIDNotFoundException e) {
                        e.printStackTrace();
                    }
                }


            this.globalServerList.remove(name);

            liptonMaster.getColouredConsoleProvider().info("Stopping Server: " + serverMeta.getServerName() + " | " + serverMeta.getPort() + " | " + serverMeta.getWrapperID());
        } else {
            liptonMaster.getColouredConsoleProvider().error("Could not unregister Server: '" + serverName + "' (§cServer was not in GlobalServer-List§r)!");
        }
    }
    //</editor-fold>

    //SERVER MANAGER UTILS

    //<editor-fold desc="ServerManager Methods">
    public int getStartedServerByGroup(ServerGroupMeta serverGroupMeta){
        int count = 0;
        for (ServerMeta serverMeta : getStartedServers()){
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                count++;
            }
        }
        return count;
    }
    public int getOnlineServerByGroup(ServerGroupMeta serverGroupMeta){
        int count = 0;
        for (ServerMeta serverMeta : getOnlineServers()){
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                count++;
            }
        }
        return count;
    }
    public int getGlobalStartedAndOnlineServerByGroup(ServerGroupMeta serverGroupMeta){
        int count = 0;
        for (ServerMeta serverMeta : getOnlineServers()){
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                count++;
            }
        }
        for (ServerMeta serverMeta : getStartedServers()){
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                count++;
            }
        }
        return count;
    }
    //</editor-fold>

    //<editor-fold desc="getServers">
    public List<ServerMeta> getStartedServers(){
        List<ServerMeta> servers = new ArrayList<>();
        this.startedServer.forEach((name, server) -> {
            servers.add(server);
        });
        return servers;
    }
    public List<ServerMeta> getOnlineServers(){
        List<ServerMeta> servers = new ArrayList<>();
        this.globalServerList.forEach((name, server) -> {
            servers.add(server);
        });
        return servers;
    }
    //</editor-fold>

    //SERVER MANAGER GETTER

    //<editor-fold desc="getServersFromName">
    public ServerMeta getServersFromName(String serverName){
        for (ServerMeta serverMeta : getOnlineServers()){
            if (serverMeta.getServerName().equalsIgnoreCase(serverName))
                return serverMeta;
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="GETTER">
    public ConcurrentHashMap<String, ServerMeta> getGlobalServerList() {
        return globalServerList;
    }

    public ConcurrentHashMap<String, ServerMeta> getStartedServer() {
        return startedServer;
    }
    //</editor-fold>
}
