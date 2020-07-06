package de.crycodes.de.spacebyter.liptonbridge;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;
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
import de.crycodes.de.spacebyter.network.channel.Identifier;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.channel.Provider;
import de.crycodes.de.spacebyter.network.packet.Packet;
import org.bukkit.Bukkit;

import java.io.File;
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
    public void stopServer(String name, String wrapperID, boolean isDynamic){
        final StopServerPacket stopServerPacket = new StopServerPacket(name, wrapperID, isDynamic);
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
                        false,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        "\n§b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n§7Players: {PLAYERS}",
                        "\n§7Server: {SERVER}",
                        "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bAvailable §7✸ §7We are available.",
                        "   §b§lLiptonCloud §8✸ §bIntelligent §7Cloudsystem\n         §bMaintenance §7✸ §7We are in maintenance mode."
                        ,"§7[§bWartungsmodus§7]",
                        50,
                        true,
                        "§bLipton Cloud\n§7We are in maintenance mode", "server_start_message", "server_stop_message", "server_online_message", false);
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

    //<editor-fold desc="getProxyMeta">
    //ONLY PROXY //TODO: DO THIS
    public ProxyMeta getProxyMeta(){
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="getServerMeta">
    //ONLY SERVER
    public ServerMeta getServerMeta(){
        Document document;
        File metaFile = new File("./META.json");
        document = Document.loadDocument(metaFile);

        String name = document.getString("NAME");
        String wrapperID = document.getString("WRAPPER-ID");
        String group = document.getString("GROUP");
        Integer serverID = document.getInt("ID");
        Boolean dynamic = document.getBoolean("DYNAMIC");
        Boolean maintenance = document.getBoolean("MAINTENANCE");

        ServerMeta serverMeta = new ServerMeta(name, serverID, new ServerGroupMeta(group, 512, 128,dynamic, maintenance, 0, 0 ), wrapperID, "127.0.0.1", Bukkit.getPort());
        return serverMeta;
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
            LiptonSpigotBridge.getInstance().getSpigotMasterClient().getThunderClient().sendPacket(LiptonSpigotBridge.getInstance().getSpigotMasterClient().getNetworkChannel(), packet);
        } else {
            LiptonBungeeBridge.getInstance().getBungeeMasterClient().getThunderClient().sendPacket(LiptonBungeeBridge.getInstance().getBungeeMasterClient().getNetworkChannel(), packet);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Channel">
    public NetworkChannel CloudChannel = new NetworkChannel(new Identifier("Master_Wrapper"), new Provider("LIPTON"));
    //</editor-fold>
}
