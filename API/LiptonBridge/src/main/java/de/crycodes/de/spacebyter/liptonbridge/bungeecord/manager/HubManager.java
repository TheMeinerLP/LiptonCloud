package de.crycodes.de.spacebyter.liptonbridge.bungeecord.manager;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.Random;

/**
 * Coded By CryCodes
 * Class: HubManager
 * Date : 07.07.2020
 * Time : 13:48
 * Project: LiptonCloud
 */

public class HubManager implements Listener {

    public void send(ProxiedPlayer player) {
        if (player.getServer().getInfo().getName().contains("Lobby")){
            player.sendMessage(LiptonBungeeBridge.getInstance().getPREFIX() + "Du bist bereits auf einer Lobby!");
        } else {
            sendPlayerToFallback(player);
        }
    }

    @EventHandler
    public void onKick(ServerKickEvent event) {
        final ProxiedPlayer player = event.getPlayer();

        ServerInfo serverInfo = event.getPlayer().getServer().getInfo();
        if(!serverInfo.getName().contains("Lobby")) {
            event.setCancelled(true);
            sendPlayerToFallback(player);
            return;
        }
        System.out.println(event.getKickReason());
        if(event.getKickReason().contains("closed")) {
            event.setCancelled(true);
            sendPlayerToFallback(player);
            return;
        }
        event.setCancelled(false);

    }

    private void sendPlayerToFallback(ProxiedPlayer player){
        ArrayList<String> fallback = new ArrayList<>();

        ProxyServer.getInstance().getServers().forEach((name, server) ->{
            if(name.contains("Lobby")){
                fallback.add(name);
                System.out.println(fallback.toArray().toString());
            }
        });

        String randomServer = fallback.get(new Random().nextInt(fallback.size()));
        ServerInfo info = LiptonBungeeBridge.getInstance().getProxy().getServerInfo(randomServer);

        if(info != null)
            LiptonBungeeBridge.getInstance().getProxy().getPlayer(player.getName()).connect(info);
    }

}