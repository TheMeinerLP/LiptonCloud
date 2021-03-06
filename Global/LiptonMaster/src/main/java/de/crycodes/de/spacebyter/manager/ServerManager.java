package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.ServerGroupConfig;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerAutoStartEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerRegisterEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerStartEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerUnregisterEvent;
import de.crycodes.de.spacebyter.liptoncloud.exceptions.ServerIDNotFoundException;
import de.crycodes.de.spacebyter.liptoncloud.exceptions.ServerNotStartedException;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;
import de.crycodes.de.spacebyter.network.packet.ReceiverType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Coded By CryCodes
 * Class: ServerManager
 * Date : 24.06.2020
 * Time : 19:59
 * Project: LiptonCloud
 */

public class ServerManager implements Runnable {

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

        liptonMaster.getPool().submit(this);
    }
    //</editor-fold>

    //<editor-fold desc="Start - Stop Logic">
    @Override
    public void run() {
        tick = liptonMaster.getScheduler().scheduleAsyncWhile(new Runnable() {
            @Override
            public void run() {
                List<ServerGroupMeta> availableServerGroups = serverGroupConfig.getServerMetas();

                if (liptonMaster.getWrapperManager().getBestFreeWrapper() == null) return;

                availableServerGroups.forEach(serverGroupMeta -> {

                    final WrapperMeta wrapperMeta = liptonMaster.getWrapperManager().getBestFreeWrapper();
                    final String bestWrapperID = wrapperMeta.getWrapperConfig().getWrapperId();


                    if (getOnlineServerByGroup(liptonMaster.getServerGroupConfig().getServerMetaByName("Lobby")) == 0 &&
                            getStartedServerByGroup(liptonMaster.getServerGroupConfig().getServerMetaByName("Lobby")) == 0 ){

                        startServer(liptonMaster.getServerGroupConfig().getServerMetaByName("Lobby"), bestWrapperID, true);
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
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if (getStartedServerByGroup(serverGroupMeta) >= serverGroupMeta.getMaxMemory()) {
            liptonMaster.getCloudConsole().getLogger().error("To many Server's are running of group " + serverGroupMeta.getGroupName());
            return;
        }
        int port = liptonMaster.getPortManager().getFreePort();
        int id = liptonMaster.getIdManager().getFreeID(serverGroupMeta.getGroupName());
        final ServerMeta serverMeta = new ServerMeta(serverGroupMeta.getGroupName() + liptonMaster.getMasterConfig().getServerNameSplitter() + id ,id, serverGroupMeta, wrapperID, "127.0.0.1", port);
        this.startedServer.put(serverMeta.getServerName().toUpperCase() ,serverMeta);

        liptonMaster.getMasterProxyServer().getServer().sendPacket(
                liptonMaster.getMasterProxyServer().getNetworkChannel(),
                new StartServerPacket(wrapperID ,serverMeta, ReceiverType.BUNGEECORD));

        liptonMaster.getMasterWrapperServer().sendPacket(new StartServerPacket(wrapperID, serverMeta, ReceiverType.WRAPPER));

        if (!autoStart){
            liptonMaster.getEventManager().callEvent(new ServerStartEvent(serverMeta));
            this.liptonMaster.getCloudConsole().getLogger().info("StartetServer: " + serverMeta.getServerName() + " | on port: " + serverMeta.getPort() + " | on wrapper: " + serverMeta.getWrapperID() + " | group: " + serverGroupMeta.getGroupName() + " !");
        }
        if (autoStart) {
            liptonMaster.getEventManager().callEvent(new ServerAutoStartEvent(serverMeta));
            liptonMaster.getCloudConsole().getLogger().info("§cAUTOSTART:§r StartedServer '" + serverGroupMeta.getGroupName() + "' stats: (" + getGlobalStartedAndOnlineServerByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ") Port: §a" + port + "§r | ID: §a" + id + "§r !");
        }
    }
    //</editor-fold>

    //<editor-fold desc="Register Method">
    public void registerServer(String serverName){
        final String name = serverName.toUpperCase();

        if (name.equalsIgnoreCase("NONE")){
            liptonMaster.getCloudConsole().getLogger().error("Dummy-Server tried to register");
            return;
        }


        if (!this.startedServer.containsKey(name)){
            liptonMaster.getCloudConsole().getLogger().error("Server with name: '" + serverName + "' was not found in StartServer-List!");
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
        liptonMaster.getEventManager().callEvent(new ServerRegisterEvent(serverMeta));
        liptonMaster.getCloudConsole().getLogger().info("Registered new Spigot Server: (§c" + serverName + "§r)!");

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
            liptonMaster.getEventManager().callEvent(new ServerUnregisterEvent(serverMeta));

            liptonMaster.getCloudConsole().getLogger().info("Stopping Server: " + serverMeta.getServerName() + " | " + serverMeta.getPort() + " | " + serverMeta.getWrapperID());
        } else {
            liptonMaster.getCloudConsole().getLogger().error("Could not unregister Server: '" + serverName + "' (§cServer was not in GlobalServer-List§r)!");
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
    public List<ServerMeta> getOnlineServersByGroup(String name){
        List<ServerMeta> serverMetas = new ArrayList<>();
        this.getOnlineServers().forEach(serverMeta -> {
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(name))
                serverMetas.add(serverMeta);
        });
        return serverMetas;
    }

    //SERVER MANAGER GETTER

    //<editor-fold desc="getServersFromName">
    public ServerMeta getServersFromName(String serverName){
        for (ServerMeta serverMeta : getOnlineServers()){
            if (serverMeta.getServerName().equalsIgnoreCase(serverName))
                return serverMeta;
        }
        return null;
    }
    public ServerMeta getServersFromID(Integer id){
        for (ServerMeta serverMeta : getOnlineServers()){
            if (serverMeta.getId().equals(id))
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
