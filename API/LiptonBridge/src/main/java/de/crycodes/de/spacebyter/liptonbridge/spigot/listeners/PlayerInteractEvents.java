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

/*
    Coded by SpaceByter & DynamicPacket!
    Datum: 07.17.2020 TIME: 22:24 AM
 */

public class PlayerInteractEvents implements Listener {

    private final LiptonSpigotBridge plugin;

    public PlayerInteractEvents(LiptonSpigotBridge plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(final PlayerInteractEvent event) {

        if(event.getClickedBlock() == null)
            return;

        if(event.getClickedBlock().getType() == Material.AIR)
            return;

        final Action action = event.getAction();


        switch (action) {
            case RIGHT_CLICK_BLOCK:
            case LEFT_CLICK_BLOCK: {

                if(!(event.getClickedBlock().getType().equals(Material.WALL_SIGN)))
                    break;
                Sign sign = (Sign) event.getClickedBlock().getState();
                if(sign.getLine(0) == null || sign.getLine(0).equalsIgnoreCase(""))
                    break;
                try {
                    sendPlayerToServer(replace(sign.getLine(0)), event.getPlayer());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                break;
            }

        }
    }

    //<editor-fold desc="replace">
    public String replace(String text) {
        for(String replaces : REPLACE_FIX) {
            if(text.contains(replaces)) {
                text = text.replace(replaces, "");
            }
        }
        return text;
    }
    //</editor-fold>

    //<editor-fold desc="REPLACE_FIX">
    private String[] REPLACE_FIX = {
            "§c","§6","§e", "§2","§a",
            "§b", "§3", "§1", "§9",
            "§d", "§5", "§f", "§7",
            "§8", "§0", "§l", "§o",
            "§r", "§m", "§n", "§k",
            ""
    };
    //</editor-fold>

    //<editor-fold desc="sendPlayerToServer">
    private void sendPlayerToServer(String servername, Player player) throws IOException {
        ByteArrayOutputStream stream = null;
        DataOutputStream outputStream = null;
        try {
            stream = new ByteArrayOutputStream();
            outputStream = new DataOutputStream(stream);
            outputStream.writeUTF("connect");
            outputStream.writeUTF(servername);
            player.sendPluginMessage(LiptonSpigotBridge.getInstance(), "BungeeCord", stream.toByteArray());
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            stream.close();
            outputStream.close();
        }
    }
    //</editor-fold>

}
