package de.crycodes.de.spacebyter.liptonbridge.spigot.listeners;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 20:57                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class ClickListener implements Listener {

    @EventHandler
    public void onClick(final PlayerInteractEvent event) {

        final Player player = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getClickedBlock().getType().equals(Material.WALL_SIGN)){
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0) != null){
                    sendPlayer(replace(sign.getLine(0)), player);
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }


    }
    private void sendPlayer(String servername, Player player){
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(servername);
            player.sendPluginMessage(LiptonSpigotBridge.getInstance(), "BungeeCord", b.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String replace(String text){
        String end = text.replace("§4", "")
                .replace("§c", "")
                .replace("§6", "")
                .replace("§e", "")
                .replace("§2", "")
                .replace("§a", "")
                .replace("§b", "")
                .replace("§3", "")
                .replace("§1", "")
                .replace("§9", "")
                .replace("§d", "")
                .replace("§5", "")
                .replace("§f", "")
                .replace("§7", "")
                .replace("§8", "")
                .replace("§0", "")
                .replace("§l", "")
                .replace("§o", "")
                .replace("§r", "")
                .replace("§n", "")
                .replace("§m", "")
                .replace("§k", "")
                .replace("", "");
        return end;
    }
}
