package de.crycodes.de.spacebyter.liptonbridge.bungeecord.listener;


import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
        if(!(plugin.getProxyConfig().isUseProxyConfig())) return;
        ServerPing ping = event.getResponse();
        ping.getPlayers().setMax(plugin.getProxyConfig().getMaxPlayer());

        if(plugin.getProxyConfig().getMaintenance()) {
            ServerPing.Protocol version = ping.getVersion();
            version.setName(plugin.getProxyConfig().getMaintenanceVersionString().replace("&", "§"));
            version.setProtocol(2);
            event.setResponse(ping);
            event.getResponse().setDescriptionComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getProxyConfig().getMaintenance_motd())));
            return;
        }
        event.getResponse().setDescriptionComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getProxyConfig().getDefault_motd())));
    }

    @EventHandler
    public void PlayerSwitchEvent(ServerSwitchEvent event) {

        if(!(plugin.getProxyConfig().isUseProxyConfig())) return;

        String tablistTop = plugin.getProxyConfig().getTablist_top().replace("{SERVER}", event.getPlayer().getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "").replace("&", "§");
        String tablistBottom = plugin.getProxyConfig().getTablist_bottom().replace("{SERVER}", event.getPlayer().getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "").replace("&", "§");

        String tabListTopColoredTranslation = ChatColor.translateAlternateColorCodes('&', tablistTop);
        String tabListColoredTranslation = ChatColor.translateAlternateColorCodes('&', tablistBottom);

        event.getPlayer().setTabHeader(new TextComponent(tabListTopColoredTranslation), new TextComponent(tabListColoredTranslation));
    }

    @EventHandler
    public void PostLoginEvent(PostLoginEvent event) {
        try {
            if(!(plugin.getProxyConfig().isUseProxyConfig())) return;

            if (plugin.getCloudAPI().getProxyConfig().getMaintenance()){
                if(!(plugin.getProxyConfig().getMaintenancePlayer().contains(event.getPlayer().getName()))) {
                    event.getPlayer().disconnect(new TextComponent(plugin.getProxyConfig().getMaintenanceKickMessage()));
                    return;
                }
            }
            final ProxiedPlayer player = event.getPlayer();
            String tablistTop = plugin.getProxyConfig().getTablist_top().replace("{SERVER}", player.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");
            String tablistBottom = plugin.getProxyConfig().getTablist_bottom().replace("{SERVER}", player.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");

            ChatColor.translateAlternateColorCodes('&', tablistTop);
            ChatColor.translateAlternateColorCodes('&', tablistBottom);

            player.setTabHeader(new TextComponent(tablistTop), new TextComponent(tablistBottom));

            if (plugin.getProxy().getPlayers().isEmpty()) return;

            plugin.getProxy().getPlayers().forEach(players -> {

                String tablistTopallAll = plugin.getProxyConfig().getTablist_top().replace("{SERVER}", players.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");
                String tablistBottomAll = plugin.getProxyConfig().getTablist_bottom().replace("{SERVER}", players.getServer().getInfo().getName()).replace("{PLAYERS}", plugin.getProxy().getPlayers().size() + "");

                ChatColor.translateAlternateColorCodes('&', tablistTopallAll);
                ChatColor.translateAlternateColorCodes('&', tablistBottomAll);

                players.setTabHeader(new TextComponent(tablistTopallAll.replace("&", "§")), new TextComponent(tablistBottomAll.replace("&", "§")));

            });
        } catch (Exception ignored){ }

    }
}
