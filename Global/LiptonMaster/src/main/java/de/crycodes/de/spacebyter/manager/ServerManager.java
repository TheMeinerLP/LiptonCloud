package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.in.StartServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldRunAsync;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Coded By CryCodes
 * Class: ServerManager
 * Date : 24.06.2020
 * Time : 19:59
 * Project: LiptonCloud
 */

public class ServerManager {

    private int task;
    private List<ServerMeta> globalserverrlist = new ArrayList<>();
    private List<ServerGroupMeta> availableServerGroups = new LinkedList<>();

    private final LiptonMaster liptonMaster;
    private final Scheduler scheduler;

    public ServerManager( @ShouldNotBeNull LiptonMaster liptonMaster, @ShouldNotBeNull Scheduler scheduler) {
        this.liptonMaster = liptonMaster;
        this.scheduler = scheduler;
    }

    public void reload(){
        this.availableServerGroups = liptonMaster.getServerGroupConfig().getServerMetas();
    }

    @ShouldRunAsync
    public void start(){
        this.reload();
        if (liptonMaster.getServerGroupConfig().getServerMetas() == null || liptonMaster.getServerGroupConfig().getServerMetas().isEmpty()){
            liptonMaster.getColouredConsoleProvider().error("No ServerGroups Found!");
            return;
        }
        task = scheduler.scheduleAsyncWhile(new Runnable() {
            @Override
            public void run() {
                for (ServerGroupMeta serverGroupMeta : availableServerGroups){
                    if (getStartetServerGroupAsAmountByGroup(serverGroupMeta) < serverGroupMeta.getMinServer()){

                        if (LiptonMaster.getInstance().getWrapperManager().getBestFreeWrapper() == null) return;

                        WrapperMeta wrapperMeta = LiptonMaster.getInstance().getWrapperManager().getBestFreeWrapper();

                        startServer(serverGroupMeta, wrapperMeta.getWrapperConfig().getWrapperId(), true);

                    } else {
                        if (liptonMaster.getMasterConfig().isDebugMode())
                            liptonMaster.getColouredConsoleProvider().debug("§c[AUTOSTART]§r Server-Group is complete: " + serverGroupMeta.getGroupName() + " stats: (" + getStartetServerGroupAsAmountByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ")");
                    }
                }
            }
        }, 2500, 2500);
    }



    public void startServer( @ShouldNotBeNull ServerGroupMeta serverGroupMeta, String wrapperID, boolean autoStart){
        if (getStartetServerGroupAsAmountByGroup(serverGroupMeta) >= serverGroupMeta.getMaxMemory()) {
            liptonMaster.getColouredConsoleProvider().error("To many Server's are running of group " + serverGroupMeta.getGroupName());
            return;
        }
        int port = liptonMaster.getPortManager().getFreePort();
        int id = liptonMaster.getIdManager().getFreeID(serverGroupMeta);
        final ServerMeta serverMeta = new ServerMeta(serverGroupMeta.getGroupName() + liptonMaster.getMasterConfig().getServerNameSplitter() + id ,id, serverGroupMeta, wrapperID, "127.0.0.1", port);
        this.globalserverrlist.add(serverMeta);

        liptonMaster.getMasterProxyServer().getServer().sendPacket(
                liptonMaster.getMasterProxyServer().getNetworkChannel(),
                new StartServerPacket(wrapperID,serverMeta));

        LiptonMaster.getInstance().getMasterWrapperServer().sendPacket(new StartServerPacket(wrapperID, serverMeta));

        if (!autoStart) this.liptonMaster.getColouredConsoleProvider().info("StartetServer: " + serverMeta.getServerName() + " | on port: " + serverMeta.getPort() + " | on wrapper: " + serverMeta.getWrapperID() + " | group: " + serverGroupMeta.getGroupName() + " !");
        if (autoStart)  liptonMaster.getColouredConsoleProvider().info("§c[AUTOSTART]§r StartetServer '" + serverGroupMeta.getGroupName() + "' stats: (" + getStartetServerGroupAsAmountByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ") Port: §a" + port + "§r | ID: §a" + id + "§r !");
    }

    public void removeServer(@ShouldNotBeNull ServerMeta serverMeta){

        if (!this.getGlobalserverrlist().contains(serverMeta)) return;

        if (getServerByName(serverMeta.getServerName()) == null) return;

        if (liptonMaster.getPortManager().getPortlist().contains(serverMeta.getPort()))
            liptonMaster.getPortManager().removePort(serverMeta.getPort());

        if (!(liptonMaster.getIdManager().getServerIdList().get(serverMeta.getServerGroupMeta()) == null)){
            if (liptonMaster.getIdManager().getServerIdList().get(serverMeta.getServerGroupMeta()).contains(serverMeta.getId())){
                liptonMaster.getIdManager().removeID(serverMeta.getServerGroupMeta(),serverMeta.getPort());

            }

        }

        liptonMaster.getColouredConsoleProvider().info("Stopping Server: " + serverMeta.getServerName() + " | " + serverMeta.getPort() + " | " + serverMeta.getWrapperID());

        this.globalserverrlist.remove(serverMeta);

        liptonMaster.getMasterProxyServer().getServer().sendPacket(
                liptonMaster.getMasterProxyServer().getNetworkChannel(),
                new StopServerPacket(serverMeta.getServerName(), serverMeta.getWrapperID(), serverMeta.getServerGroupMeta().isDynamicService()));

        liptonMaster.getMasterWrapperServer().sendPacket(new StopServerPacket(serverMeta.getServerName(), serverMeta.getWrapperID(), serverMeta.getServerGroupMeta().isDynamicService()));

    }

    public void replaceServer(@ShouldNotBeNull ServerMeta serverMeta){
        if (this.globalserverrlist.contains(serverMeta))
            this.globalserverrlist.remove(serverMeta);
        this.globalserverrlist.add(serverMeta);
    }

    public int getTask() {
        return task;
    }

    //UTIL SHIT
    private int getStartetServerGroupAsAmountByGroup(ServerGroupMeta serverGroupMeta){
        AtomicInteger amount = new AtomicInteger();
        this.globalserverrlist.forEach((serverMeta) -> {
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                amount.getAndIncrement();
            }
        });
        return amount.get();
    }
    public ServerMeta getServerByName(String servername){
        for (ServerMeta serverMeta : globalserverrlist){
            if (serverMeta.getServerName().equalsIgnoreCase(servername))
                return serverMeta;
        }
        return null;
    }

    public List<ServerMeta> getGlobalserverrlist() {
        return globalserverrlist;
    }
}
