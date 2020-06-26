package de.crycodes.de.spacebyter.liptonbridge.bungeecord.listener;


import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnectEvent implements Listener {

    private LiptonBungeeBridge plugin;

    public PlayerConnectEvent(LiptonBungeeBridge plugin) {
        this.plugin = plugin;
        this.plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void ProxyPingEvent(ProxyPingEvent event) {
        if(!(plugin.getCloudAPI().getProxyConfig().isUseProxyConfig())) return;
        ServerPing ping = event.getResponse();
        ping.getPlayers().setMax(plugin.getCloudAPI().getProxyConfig().getMaxPlayer());

        if(plugin.getCloudAPI().getProxyConfig().getMaintenance()) {
            ServerPing.Protocol version = ping.getVersion();
            version.setName(plugin.getCloudAPI().getProxyConfig().getMaintenanceVersionString());
            version.setProtocol(2);
            event.setResponse(ping);
            event.getResponse().setDescriptionComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getCloudAPI().getProxyConfig().getMaintenance_motd())));
            return;
        }
        event.getResponse().setDescriptionComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getCloudAPI().getProxyConfig().getDefault_motd())));
    }

    @EventHandler
    public void PlayerSwitchEvent(ServerSwitchEvent event) {

        if(!(plugin.getCloudAPI().getProxyConfig().isUseProxyConfig())) return;

        String tablistTop = plugin.getCloudAPI().getProxyConfig().getTablist_top().replace("{SERVER}", event.getPlayer().getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");
        String tablistBottom = plugin.getCloudAPI().getProxyConfig().getTablist_bottom().replace("{SERVER}", event.getPlayer().getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");

        ChatColor.translateAlternateColorCodes('&', tablistTop);
        ChatColor.translateAlternateColorCodes('&', tablistBottom);

        event.getPlayer().setTabHeader(new TextComponent(tablistTop), new TextComponent(tablistBottom));
    }

    @EventHandler
    public void PostLoginEvent(PostLoginEvent event) {
        if(!(plugin.getCloudAPI().getProxyConfig().isUseProxyConfig())) return;

        if(!(plugin.getCloudAPI().getProxyConfig().getMaintenancePlayer().contains(event.getPlayer().getName()))) {
            event.getPlayer().disconnect(new TextComponent(plugin.getCloudAPI().getProxyConfig().getMaintenanceKickMessage()));
            return;
        }

        plugin.getProxy().getPlayers().forEach(players -> {

            String tablistTop = plugin.getCloudAPI().getProxyConfig().getTablist_top().replace("{SERVER}", players.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");
            String tablistBottom = plugin.getCloudAPI().getProxyConfig().getTablist_bottom().replace("{SERVER}", players.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");

            ChatColor.translateAlternateColorCodes('&', tablistTop);
            ChatColor.translateAlternateColorCodes('&', tablistBottom);

            players.setTabHeader(new TextComponent(tablistTop), new TextComponent(tablistBottom));

        });
    }
}
