package de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.*;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

/**
 * Coded By CryCodes
 * Class: ServerCommand
 * Date : 07.07.2020
 * Time : 14:30
 * Project: LiptonCloud
 */

public class ServerCommand extends Command implements TabExecutor {

    public ServerCommand() {
        super("cserver");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!LiptonBungeeBridge.getInstance().getProxyConfig().getCloudAdminPlayers().contains(sender.getName())){
            sender.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "No Permissions to Execute this Command!");
            return;
        }
        Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
        if (args.length == 0) {
            if (sender instanceof ProxiedPlayer)
                sender.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "current Server: " + ((ProxiedPlayer)sender).getServer().getInfo().getName());
            for (ServerInfo server : servers.values()) {
                sender.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + server.getName());
            }
        } else {
            if (!(sender instanceof ProxiedPlayer))
                return;
            ProxiedPlayer player = (ProxiedPlayer)sender;
            ServerInfo server = servers.get(args[0]);
            if (server == null) {
                player.sendMessage(ProxyServer.getInstance().getTranslation("no_server", new Object[0]));
            } else if (!server.canAccess((CommandSender)player)) {
                player.sendMessage(ProxyServer.getInstance().getTranslation("no_server_permission", new Object[0]));
            } else {
                player.connect(server, ServerConnectEvent.Reason.COMMAND);
            }
        }
    }

    public Iterable<String> onTabComplete(final CommandSender sender, final String[] args) {
        if (!LiptonBungeeBridge.getInstance().getProxyConfig().getCloudAdminPlayers().contains(sender.getName())){
            sender.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "No Permissions to Execute this Command!");
            return new ArrayList<>();
        }
        return (args.length > 1) ? Collections.EMPTY_LIST : Iterables.transform(Iterables.filter(ProxyServer.getInstance().getServers().values(), new Predicate<ServerInfo>() {
            private final String lower = (args.length == 0) ? "" : args[0].toLowerCase(Locale.ROOT);

            public boolean apply(ServerInfo input) {
                return (input.getName().toLowerCase(Locale.ROOT).startsWith(this.lower) && input.canAccess(sender));
            }
        }), new Function<ServerInfo, String>() {
            public String apply(ServerInfo input) {
                return input.getName();
            }
        });
    }


}