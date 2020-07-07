package de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerMeta;
import de.crycodes.de.spacebyter.liptoncloud.packets.global.UpdateMaintenancePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.proxy.out.ServerGroupUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerGroupPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.in.StopServerPacket;
import de.crycodes.de.spacebyter.liptoncloud.packets.server.server.out.ServerUpdatePacket;
import de.crycodes.de.spacebyter.liptoncloud.utils.ExitUtil;
import de.crycodes.de.spacebyter.liptoncloud.utils.annotiations.ShouldNotBeNull;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CloudCommand extends Command implements TabExecutor {

    private final LiptonBungeeBridge plugin;


    public CloudCommand(LiptonBungeeBridge plugin) {
        super("cloud");
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    public void execute(CommandSender sender, String[] args) {

        if(!(sender instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (!plugin.getProxyConfig().getCloudAdminPlayers().contains(player.getName())){
            player.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "No Permissions to Execute this Command!");
            return;
        }

        switch (args.length) {
            case 0:
                this.sendUsage(player);
                break;
            case 1:
                if(args[0].equalsIgnoreCase("version")) {
                    player.sendMessage(plugin.getPREFIX() + "§7LiptonCloud RELEASE #1.0 by CryCodes & SpaceByter & ErazeYT");
                    break;
                }

                if(args[0].equalsIgnoreCase("listGroups")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Groups:");
                    plugin.getCloudAPI().getAvailableServerGroups().forEach(groups -> {
                        player.sendMessage(plugin.getPREFIX() + "§7Group: [§b" + groups.getGroupName() + " §7| §b" + groups.getMaxMemory() + "MB "  + "§7]");
                    });
                    break;
                }
                if(args[0].equalsIgnoreCase("listProxys")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Proxys:");
                    plugin.getCloudAPI().getProxyConfig().getGlobalProxys().forEach(proxys -> {
                        player.sendMessage(plugin.getPREFIX() + "§7Proxy: [§b" + proxys.getName() + " §7| §b" + proxys.getId()  + "§7]");
                    });
                    break;
                }
                if(args[0].equalsIgnoreCase("listServers")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Servers:");
                    plugin.getCloudAPI().getAvailableServers().forEach(servers -> {
                        player.sendMessage(plugin.getPREFIX() + "§7Server: [§b" + servers.getServerName() + " | W-ID §b" + servers.getWrapperID() + "§7]");
                    });
                    break;
                }

                if(args[0].equalsIgnoreCase("listWrappers")) {
                    player.sendMessage(plugin.getPREFIX() + "§7Wrappers:");
                    plugin.getCloudAPI().getProxyConfig().getGlobalWrappers().forEach(wrappers -> {
                        player.sendMessage(plugin.getPREFIX() + "Wrapper: §7[§b" + wrappers.getWrapperConfig().getWrapperId() + "§7/§b" + wrappers.getWrapperConfig().getHost() + "§7]");
                    });
                    break;
                }

                if(args[0].equalsIgnoreCase("list")) {
                    player.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "Online: ");
                    plugin.getCloudAPI().getProxyConfig().getGlobalWrappers().forEach(wrappers -> {
                        player.sendMessage(plugin.getPREFIX() + "Wrapper: §7[§b" + wrappers.getWrapperConfig().getWrapperId() + "§7/§b" + wrappers.getWrapperConfig().getHost() + "§7]");
                    });
                    plugin.getCloudAPI().getProxyConfig().getGlobalProxys().forEach(proxys -> {
                        player.sendMessage(plugin.getPREFIX() + "Proxy: §7[§b" + proxys.getName() + "§7]");
                    });
                    plugin.getCloudAPI().getAvailableServerGroups().forEach(groups -> {
                        player.sendMessage(plugin.getPREFIX() + "§7Group: §b" + groups.getGroupName());
                        plugin.getCloudAPI().getAvailableServers().forEach(servers -> {
                            if(servers.getServerGroupMeta().getGroupName().equalsIgnoreCase(groups.getGroupName())) {
                                player.sendMessage(plugin.getPREFIX() + "§7Server: [§b" + servers.getServerName() + " | W-ID §b" + servers.getWrapperID() + " | PORT §b" + servers.getPort()  + "§7]");
                            }
                        });
                    });
                    break;
                }

                this.sendUsage(player);
                break;
            default:
                this.sendUsage(player);
        }

    }

    private void sendUsage(@ShouldNotBeNull ProxiedPlayer player) {
        player.sendMessage(plugin.getPREFIX() + "§bCloud Commands§7:");
        player.sendMessage(" ");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listProxys");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listServers");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listWrappers");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud listGroups");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud list");
        player.sendMessage(plugin.getPREFIX() + "§7/cloud version");
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

    //<editor-fold desc="getServerByName">
    private ServerMeta getServerByName(String name){
        for (ServerMeta serverMeta : plugin.getProxyConfig().getGlobalServers())
            if (serverMeta.getServerName().equalsIgnoreCase(name))
                return serverMeta;
        return null;
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        if (!LiptonBungeeBridge.getInstance().getProxyConfig().getCloudAdminPlayers().contains(commandSender.getName())){
            commandSender.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "No Permissions to Execute this Command!");
            return new ArrayList<>();
        } else {
            List<String> tabs = new ArrayList<>();

            tabs.add("listProxys");
            tabs.add("listServers");
            tabs.add("listWrappers");
            tabs.add("listGroups");
            tabs.add("list");
            tabs.add("version");

            return tabs;
        }
    }
    //</editor-fold>
}
