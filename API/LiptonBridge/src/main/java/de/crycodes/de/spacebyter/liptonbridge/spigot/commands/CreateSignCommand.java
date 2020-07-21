package de.crycodes.de.spacebyter.liptonbridge.spigot.commands;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CreateSignCommand implements CommandExecutor {

    private final LiptonSpigotBridge plugin;

    public CreateSignCommand(LiptonSpigotBridge plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        if(!(sender.hasPermission("liptoncloud.createSignCommand"))) return true;
        Player player = (Player) sender;

        if(args.length >= 1) {
            String serverGroup = args[0];
            if(LiptonSpigotBridge.getInstance().getCloudAPI().doesServerGroupExist(serverGroup)) {

                Set<Material> materials = new HashSet<>();
                materials.add(Material.AIR);

                if (player.getTargetBlock(materials, 5).getType().equals(Material.WALL_SIGN)) {

                    final CloudSign sign = new CloudSign(player.getTargetBlock(materials, 5).getLocation(), serverGroup);

                    plugin.getSignCreator().createSign(sign, serverGroup.toUpperCase());

                        Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                        Sign signBlock = (Sign) block.getState();

                        signBlock.setLine(0, "");
                        signBlock.setLine(1, "§c" + serverGroup.toUpperCase());
                        signBlock.setLine(2, "§cNEW SIGN");
                        signBlock.setLine(3, "");

                        signBlock.update(true);
                        replace(sign, true);

                        player.sendMessage(plugin.getPREFIX() + "§aYou created a new CloudSign.");
                        return true;

                }
                player.sendMessage(plugin.getPREFIX() + "§cYou must look at a Sign.");
                return true;
            }
            player.sendMessage(plugin.getPREFIX() + "§cThe ServerGroup " + serverGroup + " wasn't found.");
            return true;
        }
        player.sendMessage(plugin.getPREFIX() + "§7/createSign <Group>");

        return false;
    }

    //<editor-fold desc="replace">
    void replace(CloudSign sign, Boolean loading) {
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
    }
    //</editor-fold>

    //<editor-fold desc="getBlockFaced">
    Block getBlockFaced(Block block) {
        switch (block.getData()) {
            case 2:
                return block.getRelative(BlockFace.SOUTH);
            case 3:
                return block.getRelative(BlockFace.NORTH);
            case 4:
                return block.getRelative(BlockFace.EAST);
            case 5:
                return block.getRelative(BlockFace.WEST);
        }
        return block;
    }
    //</editor-fold>

}
