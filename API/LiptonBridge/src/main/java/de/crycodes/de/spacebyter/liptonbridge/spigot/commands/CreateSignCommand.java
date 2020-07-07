package de.crycodes.de.spacebyter.liptonbridge.spigot.commands;

import de.crycodes.de.spacebyter.liptonbridge.CloudAPI;
import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.de.spacebyter.liptonbridge.spigot.configs.CloudSignConfig;
import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptoncloud.LiptonLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.HashSet;
import java.util.Set;

/**********************************************************************************
 *     Urheberrechtshinweis                                                       *
 *     Copyright @ Max Fischer 2020                                               *
 *     Erstellt: 06.07.2020 / 13:14                                               *
 *                                                                                *
 *     Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.           *
 *     Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,   *
 *     bei Max Fischer. Alle Rechte vorbehalten.                                  *
 *                                                                                *
 *     Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        *
 *     öffentlichen Zugänglichmachen oder andere Nutzung                          *
 *     bedarf der ausdrücklichen, schriftlichen Zustimmung von Max Fischer        *
 *********************************************************************************/

public class CreateSignCommand implements CommandExecutor {

    private final String prefix = "§8┃ §b§lLipton §7× ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("liptoncloud.createSignCommand")) {
                if(args.length >= 1) {

                    String serverGroup = args[0];

                    if(LiptonSpigotBridge.getInstance().getCloudAPI().doesServerGroupExist(serverGroup)) {

                        final CloudSignConfig cloudSignConfig = new CloudSignConfig();

                        final Set<Material> materials = new HashSet<>();
                        materials.add(Material.AIR);

                        if (player.getTargetBlock(materials, 5).getType().equals(Material.WALL_SIGN)) {

                            final CloudSign sign = new CloudSign(player.getTargetBlock(materials, 5).getLocation(), serverGroup);
                            if(!cloudSignConfig.signAlreadyThere(sign)){
                                cloudSignConfig.addSign(sign);

                                Block block = Bukkit.getWorld(sign.getWorld()).getBlockAt(sign.getX(),sign.getY(),sign.getZ());
                                Sign signBlock = (Sign) block.getState();

                                signBlock.setLine(0, "");
                                signBlock.setLine(1, "§c" + serverGroup.toUpperCase());
                                signBlock.setLine(2, "§cNEW SIGN");
                                signBlock.setLine(3, "");

                                signBlock.update();

                                replace(sign, true);

                                player.sendMessage(prefix+"You set a new CloudSign.");
                            }else player.sendMessage(prefix+"This sign is already set.");

                        } else player.sendMessage(prefix+"You must look at a sign.");

                    }else player.sendMessage(prefix+"The ServerGroup " + serverGroup + " wasn't found.");
                }else player.sendMessage(prefix+"§7/CreateSign <Group>");
            }
        }
        return false;
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
}
