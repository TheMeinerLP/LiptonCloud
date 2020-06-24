package de.crycodes.de.spacebyter.manager;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
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
    private ConcurrentHashMap<String, ServerMeta> globalserverrlist = new ConcurrentHashMap<>();
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

                        startServer(serverGroupMeta, wrapperMeta.getWrapperConfig().getWrapperId());

                        liptonMaster.getColouredConsoleProvider().info("§c[AUTOSTART]§r StartetServer '" + serverGroupMeta.getGroupName() + "' stats: (" + getStartetServerGroupAsAmountByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ")");
                    } else {
                        if (liptonMaster.getMasterConfig().isDebugMode())
                            liptonMaster.getColouredConsoleProvider().debug("§c[AUTOSTART]§r Server-Group is complete: " + serverGroupMeta.getGroupName() + " stats: (" + getStartetServerGroupAsAmountByGroup(serverGroupMeta) + "/" + serverGroupMeta.getMinServer() + ")");
                    }
                }
            }
        }, 2500, 2500);
    }



    public void startServer( @ShouldNotBeNull ServerGroupMeta serverGroupMeta, String wrapperID){
        if (getStartetServerGroupAsAmountByGroup(serverGroupMeta) >= serverGroupMeta.getMaxMemory()) {
            liptonMaster.getColouredConsoleProvider().error("To many Server's are running of group " + serverGroupMeta.getGroupName());
            return;
        }
        int port = new Random().nextInt(10); /*TODO: SET FREE PORT*/
        int id = new Random().nextInt(10); /*TODO: SET FREE ID*/
        final ServerMeta serverMeta = new ServerMeta(serverGroupMeta.getGroupName() + liptonMaster.getMasterConfig().getServerNameSplitter() + id ,id, serverGroupMeta, wrapperID, "127.0.0.1", port);
        this.globalserverrlist.put(serverMeta.getServerName(), serverMeta);
        this.liptonMaster.getColouredConsoleProvider().info("StartetServer: " + serverMeta.getServerName() + " | on port: " + serverMeta.getPort() + " | on wrapper: " + serverMeta.getWrapperID() + " | group: " + serverGroupMeta.getGroupName() + " !");
    }
    public void stopServer( @ShouldNotBeNull ServerMeta serverMeta){
        liptonMaster.getColouredConsoleProvider().info("Stopping Server: " + serverMeta.getServerName() + " | " + serverMeta.getPort() + " | " + serverMeta.getWrapperID());
        if (this.globalserverrlist.containsKey(serverMeta.getServerName()))
            this.globalserverrlist.remove(serverMeta.getServerName());
    }

    public int getTask() {
        return task;
    }

    //UTIL SHIT
    private int getStartetServerGroupAsAmountByGroup(ServerGroupMeta serverGroupMeta){
        AtomicInteger amount = new AtomicInteger();
        this.globalserverrlist.forEach((name, serverMeta) -> {
            if (serverMeta.getServerGroupMeta().getGroupName().equalsIgnoreCase(serverGroupMeta.getGroupName())){
                amount.getAndIncrement();
            }
        });
        return amount.get();
    }
}
