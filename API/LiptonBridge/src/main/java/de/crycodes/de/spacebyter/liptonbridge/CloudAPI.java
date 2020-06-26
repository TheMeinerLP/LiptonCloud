package de.crycodes.de.spacebyter.liptonbridge;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.objects.ProxyConfig;
import de.crycodes.de.spacebyter.liptoncloud.objects.ServerConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.ShutDownPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ReloadPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.network.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Coded By CryCodes
 * Class: CloudAPI
 * Date : 25.06.2020
 * Time : 20:56
 * Project: LiptonCloud
 */
public class CloudAPI {

    private final boolean isSpigot;

    //<editor-fold desc="Constructor">
    public CloudAPI(boolean isSpigot) {
        this.isSpigot = isSpigot;
    }
    //</editor-fold>

    //<editor-fold desc="StopServer Method">
    public void stopServer(String name){
        final StopServerPacket stopServerPacket = new StopServerPacket(name);
        sendPacket(stopServerPacket);
    }
    //</editor-fold>

    //<editor-fold desc="StopServerGroup Method">
    public void stopGroup(ServerGroupMeta serverGroupMeta){
        final StopServerGroupPacket stopServerGroupPacket = new StopServerGroupPacket(serverGroupMeta, ExitUtil.STOPPED_SUCESS);
        sendPacket(stopServerGroupPacket);
    }
    //</editor-fold>

    //<editor-fold desc="doesServerGroupExist">
    public boolean doesServerGroupExist(String name){
        for (ServerGroupMeta serverGroupMeta : getAvailableServerGroups()){
            if (serverGroupMeta.getGroupName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="isServerRunning">
    public boolean isServerRunning(String serverName){
        for (ServerMeta serverMeta : getAvailableServers()){
            if (serverMeta.getServerName().equalsIgnoreCase(serverName))
                return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Configs">
    public ProxyConfig getProxyConfig(){
        if (isSpigot){
            return null;
        } else {
            if (LiptonBungeeBridge.getInstance().getProxyConfig() == null){
                return new ProxyConfig(new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        true,
                        true,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        "",
                        "",
                        "",
                        ""
                        ,"",
                        50,
                        true,
                        "");
            } else {
                return LiptonBungeeBridge.getInstance().getProxyConfig();
            }
        }

    }
    public ServerConfig getServerConfig(){
        if (isSpigot){
            if (LiptonSpigotBridge.getInstance().getServerConfig() == null){
                return new ServerConfig(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            } else {
                return LiptonSpigotBridge.getInstance().getServerConfig();
            }
        } else {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold desc="getServerInfo">
    //ONLY SPIGOT //TODO: DO THIS
    public ServerMeta getServerInfo(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="getProxyMeta">
    //ONLY PROXY //TODO: DO THIS
    public ProxyMeta getProxyMeta(){
        return null;
    }
    //</editor-fold>
    //<editor-fold desc="getServerMeta">
    //ONLY SERVER //TODO: DO THIS
    public ServerMeta getServerMeta(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="stopCloud Method">
    public void stopCloud(int id){
        this.sendPacket(new ShutDownPacket(ExitUtil.STOPPED_SUCESS));
    }
    //</editor-fold>

    //<editor-fold desc="getAvailableServerGroups">
    public List<ServerGroupMeta> getAvailableServerGroups(){
        return isSpigot ? this.getServerConfig().getGlobalServerGroups() : this.getProxyConfig().getGlobalServerGroups();
    }
    //</editor-fold>

    //<editor-fold desc="getAvailableServers">
    public List<ServerMeta> getAvailableServers(){
        return isSpigot ? this.getServerConfig().getGlobalServers() : this.getProxyConfig().getGlobalServers();
    }
    //</editor-fold>


    //<editor-fold desc="reloadCloud">
    public void reloadCloud() {
        this.sendPacket(new ReloadPacket());
    }
    //</editor-fold>

    //<editor-fold desc="sendPacket">
    public void sendPacket(Packet packet){
        if (isSpigot) {
            LiptonSpigotBridge.getInstance().getSpigotMasterClient().sendPacket(packet);
        } else {
            LiptonBungeeBridge.getInstance().getBungeeMasterClient().sendPacket(packet);
        }
    }
    //</editor-fold>
}