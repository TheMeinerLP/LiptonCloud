package de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class CloudCommand extends Command {

    private final LiptonBungeeBridge plugin;


    public CloudCommand(LiptonBungeeBridge plugin) {
        super("cloud", "liptoncloud.cloudCommand", "c");
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    public void execute(CommandSender sender, String[] args) {
        if(!(sender.hasPermission("liptoncloud.cloudCommand"))) return;
        if(!(sender instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer) sender;
        switch (args.length) {
            case 0:
                this.sendUsage(player);
                break;
            case 1:
                if(args[0].equalsIgnoreCase("version")) {
                    player.sendMessage(plugin.getPREFIX() + "§7LiptonCloud RELEASE #1.0 by CryCodes & SpaceByter");
                    break;
                }

                if(args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload")) {
                    plugin.getCloudAPI().reloadCloud();
                    player.sendMessage(plugin.getPREFIX() + "§aThe Cloud was successfully reloaded!");
                    break;
                }
                if(args[0].equalsIgnoreCase("listGroups")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Groups:");
                    plugin.getCloudAPI().getAvailableServerGroups().forEach(groups -> {
                        player.sendMessage("§7- §b" + groups.getGroupName() + " §7| §b" + groups.getMaxMemory() + "MB");
                    });
                    break;
                }
                if(args[0].equalsIgnoreCase("listProxys")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Proxys:");
                    plugin.getCloudAPI().getProxyConfig().getGlobalProxys().forEach(proxys -> {
                        player.sendMessage("§7- §b" + proxys.getName() + " §7| §b" + proxys.getId());
                    });
                    break;
                }
                if(args[0].equalsIgnoreCase("listServers")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Servers:");
                    plugin.getCloudAPI().getAvailableServers().forEach(servers -> {
                        player.sendMessage("§7- §b" + servers.getServerName() + "§7(§bPLAYERS§7) | W-ID §b" + servers.getWrapperID());
                    });
                    break;
                }

                if(args[0].equalsIgnoreCase("listWrappers")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Wrappers:");
                    plugin.getCloudAPI().getProxyConfig().getGlobalWrappers().forEach(wrappers -> {
                        player.sendMessage("§7- §b" + wrappers.getWrapperConfig().getWrapperId());
                    });
                    break;
                }

                if(args[0].equalsIgnoreCase("list")) {
                    plugin.getCloudAPI().getProxyConfig().getGlobalWrappers().forEach(wrappers -> {
                        player.sendMessage("§7[§b" + wrappers.getWrapperConfig().getWrapperId() + "§7/§b" + wrappers.getWrapperConfig().getHost() + "§7]");
                    });
                    plugin.getCloudAPI().getProxyConfig().getGlobalProxys().forEach(proxys -> {
                        player.sendMessage("§7[§b" + proxys.getName() + "§7] (PLAYERS)");
                    });
                    plugin.getCloudAPI().getAvailableServerGroups().forEach(groups -> {
                        player.sendMessage("§7Group: §b" + groups.getGroupName());
                        plugin.getCloudAPI().getAvailableServers().forEach(servers -> {
                            if(servers.getServerGroupMeta().getGroupName().equalsIgnoreCase(groups.getGroupName())) {
                                player.sendMessage(plugin.getPREFIX() + "§7[§b" + servers.getServerName() + "§7] (PLAYERS) | STATE");
                            }
                        });
                    });
                    break;
                }

                if(args[0].equalsIgnoreCase("globalMaintenance")) {
                    if(args[1].equalsIgnoreCase("toggle")) {

                        if(plugin.getCloudAPI().getProxyConfig().getMaintenance()) {
                            final UpdateMaintenancePacket updateMaintenancePacket = new UpdateMaintenancePacket(new ArrayList<String>(), new ArrayList<String>(), false);
                            plugin.getCloudAPI().sendPacket(updateMaintenancePacket);
                            player.sendMessage(plugin.getPREFIX() + "§7The Network is now in §aMaintenance §7Mode.");
                            break;
                        }
                        final UpdateMaintenancePacket updateMaintenancePacket = new UpdateMaintenancePacket(new ArrayList<String>(), new ArrayList<String>(), true);
                        plugin.getCloudAPI().sendPacket(updateMaintenancePacket);
                        player.sendMessage(plugin.getPREFIX() + "§7The Network no longer in §cMaintenance §7Mode.");
                        break;
                    }
                }

                this.sendUsage(player);
                break;
            case 2:
                if(args[0].equalsIgnoreCase("stopServer")) {
                    String name = args[1];
                    if(plugin.getCloudAPI().isServerRunning(name)) {
                        final StopServerPacket stopServerPacket = new StopServerPacket(name);
                        plugin.getCloudAPI().sendPacket(stopServerPacket);
                        player.sendMessage(plugin.getPREFIX() + "§aThe Server " + name + " was successfully stopped.");
                        break;
                    }
                    player.sendMessage(plugin.getPREFIX() + "§cThe Server " + name + " wasn't found.");
                    break;
                }

                if(args[0].equalsIgnoreCase("stopGroup")) {
                    String name = args[1];
                    for(ServerGroupMeta groupMeta : plugin.getCloudAPI().getAvailableServerGroups()) {
                        if(groupMeta.getGroupName().equalsIgnoreCase(name)) {
                            final StopServerGroupPacket stopServerGroupPacket = new StopServerGroupPacket(groupMeta, ExitUtil.STOPPED_SUCESS);
                            plugin.getCloudAPI().sendPacket(stopServerGroupPacket);
                            player.sendMessage(plugin.getPREFIX() + "§aThe Group " + name + " was successfully stopped.");
                            break;
                        }
                    }
                    player.sendMessage(plugin.getPREFIX() + "§cThe Group " + name + " wasn't found.");
                }

                if(args[0].equalsIgnoreCase("maintenance")) {
                    String name = args[1];
                    for(ServerGroupMeta current : plugin.getCloudAPI().getAvailableServerGroups()) {
                        if(current.getGroupName().equalsIgnoreCase(name)) {
                            if(current.isMaintenance()) {
                                current.setMaintenance(false);
                                makeServeGroupChanges(current);
                                player.sendMessage(plugin.getPREFIX() + "§7The Group is now in §aMaintenance §7Mode.");
                                break;
                            }
                            current.setMaintenance(true);
                            makeServeGroupChanges(current);
                            player.sendMessage(plugin.getPREFIX() + "§7The Group no longer in §cMaintenance §7Mode.");
                            break;
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("globalMaintenance")) {

                    String name = args[2];

                    if(args[1].equalsIgnoreCase("add")) {

                        if(!(plugin.getCloudAPI().getProxyConfig().getMaintenancePlayer().contains(name))) {

                            List<String> addPlayers = new ArrayList<>();
                            addPlayers.add(name);

                            final UpdateMaintenancePacket updateMaintenancePacket = new UpdateMaintenancePacket(addPlayers, new ArrayList<String>(), plugin.getCloudAPI().getProxyConfig().getMaintenance());
                            plugin.getCloudAPI().sendPacket(updateMaintenancePacket);
                            player.sendMessage(plugin.getPREFIX() + "§7The Player §b" + name + " §7is now on the whitelist.");
                            break;
                        }
                        player.sendMessage(plugin.getPREFIX() + "§7The Player §b" + name + " §7is already on the whitelist.");
                        break;
                    }

                    if(args[1].equalsIgnoreCase("remove")) {

                        if(plugin.getCloudAPI().getProxyConfig().getMaintenancePlayer().contains(name)) {

                            List<String> removePlayer = new ArrayList<>();
                            removePlayer.add(name);

                            final UpdateMaintenancePacket updateMaintenancePacket = new UpdateMaintenancePacket(new ArrayList<String>(), removePlayer, plugin.getCloudAPI().getProxyConfig().getMaintenance());
                            plugin.getCloudAPI().sendPacket(updateMaintenancePacket);
                            player.sendMessage(plugin.getPREFIX() + "§7The Player §b" + name + " §7is no longer on the whitelist.");
                            break;
                        }
                        player.sendMessage(plugin.getPREFIX() + "§7The Player §b" + name + " §7isn't on the whitelist.");
                        break;
                    }
                }

                this.sendUsage(player);
                break;
        }

    }

    private void sendUsage(@ShouldNotBeNull ProxiedPlayer player) {
        player.sendMessage(plugin.getPREFIX() + "§bCloud Commands§7:");
        player.sendMessage(" ");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud maintenance <Group>"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud globalMaintenance <add | remove> <Player>"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud globalMaintenance <toggle>"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud rl"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listOnline");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listProxys"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listServers"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listWrappers"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listGroups"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud stopGroup <Group>"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud stopServer <Server>"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud list"); //Yes
        player.sendMessage(plugin.getPREFIX() + "§7/cloud version"); //Yes
    }

    //<editor-fold desc="makeServerChanges">
    public void makeServerChanges(ServerMeta serverMeta){
        final ServerUpdatePacket packet = new ServerUpdatePacket(serverMeta);
        plugin.getCloudAPI().sendPacket(packet);
    }
    //</editor-fold>

    //<editor-fold desc="makeServeGroupChanges">
    public void makeServeGroupChanges(ServerGroupMeta serverGroupMeta){
        final ServerGroupUpdatePacket packet = new ServerGroupUpdatePacket(serverGroupMeta);
        plugin.getCloudAPI().sendPacket(packet);
    }
    //</editor-fold>
}
