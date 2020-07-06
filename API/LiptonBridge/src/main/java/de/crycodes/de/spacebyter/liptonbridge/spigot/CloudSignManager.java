package de.crycodes.de.spacebyter.liptonbridge.spigot;

import de.crycodes.de.spacebyter.liptonbridge.spigot.configs.CloudSignConfig;
import de.crycodes.de.spacebyter.liptonbridge.spigot.enums.SignState;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.SignLayout;
import de.crycodes.de.spacebyter.liptonbridge.spigot.util.Pinger;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.scheduler.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitScheduler;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 17:50                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class CloudSignManager {

    private List<CloudSign> cloudSigns;
    private List<ServerMeta> globalServerList;

    private List<CloudSign> offlinesigns;

    private Scheduler scheduler;
    private SignLayout signLayout;
    private CloudSignConfig cloudSignConfig;
    private Pinger pinger;

    public CloudSignManager() {

        cloudSigns = new ArrayList<>();

        offlinesigns = new ArrayList<>();

        signLayout = new SignLayout();

        pinger = new Pinger();

        scheduler = new Scheduler();
        cloudSignConfig = new CloudSignConfig();

        start();
        startOfflineSignUpdate();

    }

    private void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LiptonSpigotBridge.getInstance(), () -> {

            offlinesigns.clear();

            List<String> serverGroups = new ArrayList<>();

            if (this.globalServerList == null) return;

            for (ServerGroupMeta serverGroupMeta : LiptonSpigotBridge.getInstance().getServerConfig().getGlobalServerGroups()){
                serverGroups.add(serverGroupMeta.getGroupName());
            }

            for (String serverGroup : serverGroups){
                List<ServerMeta> serversByGroup = getServersByName(serverGroup);
                List<CloudSign> signsByGroup = cloudSignConfig.getServerByGroup(serverGroup);

                for (CloudSign sign : signsByGroup){
                    if (serversByGroup.isEmpty()){
                        sign.setServerMeta(null);
                        setSign(sign, true);
                        replace(sign, true);
                        offlinesigns.add(sign);
                    } else {
                        ServerMeta serverMeta = getRandomServer(serversByGroup);
                        serversByGroup.remove(serverMeta);
                        sign.setServerMeta(serverMeta);
                        setSign(sign, false);
                        replace(sign, false);
                    }
                }
            }

        }, 0, 40*3);
    }
    private ServerMeta getRandomServer(List<ServerMeta> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public void startOfflineSignUpdate(){

        scheduler.scheduleAsyncWhile(new Runnable() {
            int tick = 0;

            @Override
            public void run() {

                switch (tick){
                    case 0:
                        for (CloudSign sign : offlinesigns){

                            Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                            Sign signBlock = (Sign) block.getState();

                            signBlock.setLine(0, signLayout.getloadingDefaultLayOut(0).get(0));
                            signBlock.setLine(1, signLayout.getloadingDefaultLayOut(0).get(1));
                            signBlock.setLine(2, signLayout.getloadingDefaultLayOut(0).get(2));
                            signBlock.setLine(3, signLayout.getloadingDefaultLayOut(0).get(3));

                            signBlock.update();

                        }
                        break;
                    case 1:
                        for (CloudSign sign : offlinesigns){

                            Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                            Sign signBlock = (Sign) block.getState();

                            signBlock.setLine(0, signLayout.getloadingDefaultLayOut(1).get(0).replace("&", "§"));
                            signBlock.setLine(1, signLayout.getloadingDefaultLayOut(1).get(1).replace("&", "§"));
                            signBlock.setLine(2, signLayout.getloadingDefaultLayOut(1).get(2).replace("&", "§"));
                            signBlock.setLine(3, signLayout.getloadingDefaultLayOut(1).get(3).replace("&", "§"));

                            signBlock.update();

                        }
                        break;
                    case 2:
                        for (CloudSign sign : offlinesigns){

                            Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                            Sign signBlock = (Sign) block.getState();

                            signBlock.setLine(0, signLayout.getloadingDefaultLayOut(2).get(0).replace("&", "§"));
                            signBlock.setLine(1, signLayout.getloadingDefaultLayOut(2).get(1).replace("&", "§"));
                            signBlock.setLine(2, signLayout.getloadingDefaultLayOut(2).get(2).replace("&", "§"));
                            signBlock.setLine(3, signLayout.getloadingDefaultLayOut(2).get(3).replace("&", "§"));

                            signBlock.update();

                        }

                        break;
                    case 3:
                        for (CloudSign sign : offlinesigns){

                            Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                            Sign signBlock = (Sign) block.getState();

                            signBlock.setLine(0, signLayout.getloadingDefaultLayOut(3).get(0).replace("&", "§"));
                            signBlock.setLine(1, signLayout.getloadingDefaultLayOut(3).get(1).replace("&", "§"));
                            signBlock.setLine(2, signLayout.getloadingDefaultLayOut(3).get(2).replace("&", "§"));
                            signBlock.setLine(3, signLayout.getloadingDefaultLayOut(3).get(3).replace("&", "§"));

                            signBlock.update();

                        }

                        tick = 0;
                        break;
                    default:
                        tick = 0;
                        break;
                }

                tick++;
            }
        }, 2000, 2000);
    }
    public void setSign(CloudSign sign, boolean offline){

        Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
        Sign signBlock = (Sign) block.getState();

        if(!offline) {
            String[] layout = getSignLayout(sign);

            signBlock.setLine(0, layout[0]);
            signBlock.setLine(1, layout[1]);
            signBlock.setLine(2, layout[2]);
            signBlock.setLine(3, layout[3]);
        }
        signBlock.update();
    }

    public void replace(CloudSign sign, Boolean loading) {
        Block signBlock = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
        Sign bukkitSign = (Sign) signBlock.getState();
        Block block = getBlockFaced(bukkitSign.getBlock());
        if (loading){
            block.setType(Material.STAINED_CLAY);
            block.setData((byte)14);
            return;
        }
        block.setType(Material.STAINED_CLAY);
        block.setData((byte)5);
        return;
    }

    public static Block getBlockFaced(Block b) {
        switch (b.getData()) {
            case 2:
                return b.getRelative(BlockFace.SOUTH);
            case 3:
                return b.getRelative(BlockFace.NORTH);
            case 4:
                return b.getRelative(BlockFace.EAST);
            case 5:
                return b.getRelative(BlockFace.WEST);
        }
        return b;
    }

    public List<ServerMeta> getServersByName(String group){
        List<ServerMeta> servers = new ArrayList<>();
        for (ServerMeta serverListObject : LiptonSpigotBridge.getInstance().getServerConfig().getGlobalServers()){
            if (serverListObject.getServerGroupMeta().getGroupName().equalsIgnoreCase(group)){
                servers.add(serverListObject);
            }
        }
        return servers;
    }

    public String[] getSignLayout(CloudSign cloudSign){
        List<String> lines = new ArrayList<>();

        pinger.pingServer(cloudSign.getServerMeta().getHost(), cloudSign.getServerMeta().getPort(), 2500);

        int users = pinger.getPlayerCount();
        int maxusers = pinger.getMaxPlayerCount();

        String servername = cloudSign.getServerMeta().getServerName();
        String state;

        try {
            state = SignState.valueOf(pinger.getMotd()).name();
        } catch (IllegalArgumentException exception) {
            state = SignState.UNKNOWN.name();
        }

        lines.add(signLayout.getDefaultLayOut().get(0).replace("%online%", users +"").replace("%max%", maxusers +"").replace("%server%", servername).replace("%state%", state).replace("&", "§"));
        lines.add(signLayout.getDefaultLayOut().get(1).replace("%online%", users +"").replace("%max%", maxusers +"").replace("%server%", servername).replace("%state%", state).replace("&", "§"));
        lines.add(signLayout.getDefaultLayOut().get(2).replace("%online%", users +"").replace("%max%", maxusers +"").replace("%server%", servername).replace("%state%", state).replace("&", "§"));
        lines.add(signLayout.getDefaultLayOut().get(3).replace("%online%", users +"").replace("%max%", maxusers +"").replace("%server%", servername).replace("%state%", state).replace("&", "§"));

        return lines.toArray(new String[0]);
    }

    public void sort() {
        cloudSigns.stream().sorted(((o1, o2) -> o1.getServerMeta().getServerName().compareToIgnoreCase(o2.getServerMeta().getServerName())));
        cloudSigns.stream().sorted((Comparator.comparing(o -> o.getServerMeta().getId())));
    }


    public void setGlobalServerList(List<ServerMeta> globalServerList) {
        this.globalServerList = globalServerList;
    }
}
