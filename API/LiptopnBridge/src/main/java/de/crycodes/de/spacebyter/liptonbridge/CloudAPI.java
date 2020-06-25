package de.crycodes.de.spacebyter.liptonbridge;

import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;

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

    //<editor-fold desc="StartServer Method">
    public void startServer(ServerGroupMeta serverGroupMeta){

    }
    //</editor-fold>

    //<editor-fold desc="StopServer Method">
    public void stopServer(ServerMeta serverMeta){

    }
    //</editor-fold>

    //<editor-fold desc="StopServerGroup Method">
    public void stopGroup(ServerGroupMeta serverGroupMeta){

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

    //<editor-fold desc="getServerInfo">
    //ONLY SPIGOT
    public ServerMeta getServerInfo(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="getProxyMeta">
    //ONLY PROXY
    public ProxyMeta getProxyMeta(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="stopCloud Method">
    public void stopCloud(int id){

    }
    //</editor-fold>

    //<editor-fold desc="createTemplate Method">
    public void createTemplate(String name, ServerGroupMeta serverGroupMeta){

    }
    //</editor-fold>

    //<editor-fold desc="getAvailableServerGroups">
    public List<ServerGroupMeta> getAvailableServerGroups(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="getAvailableServers">
    public List<ServerMeta> getAvailableServers(){
        return null;
    }
    //</editor-fold>


    //<editor-fold desc="isSpigot">
    public boolean isSpigot() {
        return isSpigot;
    }
    //</editor-fold>
}
