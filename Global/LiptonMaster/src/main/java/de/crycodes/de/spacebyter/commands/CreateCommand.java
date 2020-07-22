package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;
import de.crycodes.de.spacebyter.liptoncloud.events.ServerGroupCreateEvent;
import de.crycodes.de.spacebyter.liptoncloud.events.WrapperCreateEvent;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.GroupSetup;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.WrapperGroupSetup;

public class CreateCommand extends CloudCommand {

    private final LiptonMaster liptonMaster;

    //<editor-fold desc="CreateCommand">
    public CreateCommand(String name, String description, String[] aliases, LiptonMaster liptonMaster) {
        super(name, description, aliases);
        this.liptonMaster = liptonMaster;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        switch(args.length) {
            default:
                sendUsage(colouredConsoleProvider);
                break;
            case 1:

                if(args[0].equalsIgnoreCase("servergroup")) {

                    liptonMaster.getCommandManager().stop();

                    GroupSetup groupSetup = new GroupSetup();
                    groupSetup.start(liptonMaster.getCloudConsole());

                    ServerGroupMeta serverGroupMeta = new ServerGroupMeta(groupSetup.getServerName(),
                            "default", groupSetup.getMaxMem(),
                            groupSetup.getMinMem(),
                            groupSetup.isDynamic(),
                            false ,
                            groupSetup.getMaxServer(),
                            groupSetup.getMinServer());
                   liptonMaster.getServerGroupConfig().create(serverGroupMeta);

                    liptonMaster.getCommandManager().restart();
                    liptonMaster.getEventManager().callEvent(new ServerGroupCreateEvent(serverGroupMeta));

                    colouredConsoleProvider.getLogger().info("Servergroup '" + groupSetup.getServerName() + "' was successfully created!");
                    break;
                }

                if (args[0].equalsIgnoreCase("wrapper")){

                    liptonMaster.getCommandManager().stop();

                    WrapperGroupSetup wrapperGroupSetup = new WrapperGroupSetup();
                    wrapperGroupSetup.start(liptonMaster.getCloudConsole());

                    WrapperMeta wrapperMeta = new WrapperMeta(false, new WrapperConfig(wrapperGroupSetup.getGroupName(),
                            wrapperGroupSetup.getHost(),
                            wrapperGroupSetup.getAutoUpdate()));
                    liptonMaster.getWrapperConfig().create(wrapperMeta);

                    liptonMaster.getCommandManager().restart();
                    liptonMaster.getEventManager().callEvent(new WrapperCreateEvent(wrapperMeta));

                    colouredConsoleProvider.getLogger().info("Wrapper '" + wrapperGroupSetup.getGroupName() + "' was successfully created!");
                    break;
                }
                sendUsage(colouredConsoleProvider);
                break;
            case 2:

                break;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="sendUsage">
    private void sendUsage(CloudConsole colouredConsoleProvider) {
        colouredConsoleProvider.getLogger().info("create SERVERGROUP");
        colouredConsoleProvider.getLogger().info("create WRAPPER");
    }
    //</editor-fold>
}
