package de.crycodes.de.spacebyter.liptonbridge.bungeecord.commands;

import de.crycodes.de.spacebyter.liptonbridge.bungeecord.LiptonBungeeBridge;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Coded By CryCodes
 * Class: HubCommand
 * Date : 07.07.2020
 * Time : 13:46
 * Project: LiptonCloud
 */

public class HubCommand extends Command {

    public HubCommand() {
        super("hub", null, "lobby", "l", "mainserver");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(!(commandSender instanceof ProxiedPlayer)) return;

        final ProxiedPlayer player = (ProxiedPlayer) commandSender;
        LiptonBungeeBridge.getInstance().getHubManager().send(player);
    }
}
