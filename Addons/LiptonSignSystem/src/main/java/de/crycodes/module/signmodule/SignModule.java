package de.crycodes.module.signmodule;

import de.crycodes.de.spacebyter.liptonbridge.spigot.LiptonSpigotBridge;
import de.crycodes.module.signmodule.commands.CreateSignCommand;
import de.crycodes.module.signmodule.config.SignConfig;
import de.crycodes.module.signmodule.listener.PlayerInteractEvents;
import de.crycodes.module.signmodule.manager.SignCreator;
import de.crycodes.module.signmodule.manager.SignUpdater;
import de.crycodes.module.signmodule.util.ServerPinger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Coded By CryCodes
 * Class: SignModule
 * Date : 23.07.2020
 * Time : 18:30
 * Project: LiptonCloud
 */

public class SignModule extends JavaPlugin {

    private ServerPinger serverPinger;
    private SignConfig signConfig;
    private SignCreator signCreator;


    @Override
    public void onEnable() {

        serverPinger = new ServerPinger();
        signConfig = new SignConfig();
        signCreator = new SignCreator(signConfig);

        new PlayerInteractEvents(this);
        getCommand("createsign").setExecutor(new CreateSignCommand(this));

        new SignUpdater(this, LiptonSpigotBridge.getInstance());

        super.onEnable();
    }

    public ServerPinger getServerPinger() {
        return serverPinger;
    }

    public SignCreator getSignCreator() {
        return signCreator;
    }
}
