package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
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
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        switch(args.length) {
            default:
                sendUsage(colouredConsoleProvider);
                break;
            case 1:

                if(args[0].equalsIgnoreCase("servergroup")) {

                    liptonMaster.getCommandManager().stop();

                    GroupSetup groupSetup = new GroupSetup();
                    groupSetup.start(liptonMaster.getCommandManager().getScanner());

                   liptonMaster.getServerGroupConfig().create(new ServerGroupMeta(groupSetup.getServerName(),
                           groupSetup.getMaxMem(),
                           groupSetup.getMinMem(),
                           groupSetup.isDynamic(),
                           false ,
                           groupSetup.getMaxServer(),
                           groupSetup.getMinServer()));

                    liptonMaster.getCommandManager().restart();

                    colouredConsoleProvider.info("Servergroup '" + groupSetup.getServerName() + "' was successfully created!");
                    break;
                }

                if (args[0].equalsIgnoreCase("wrapper")){

                    liptonMaster.getCommandManager().stop();

                    WrapperGroupSetup wrapperGroupSetup = new WrapperGroupSetup();
                    wrapperGroupSetup.start(liptonMaster.getCommandManager().getScanner());

                    liptonMaster.getWrapperConfig().create(new WrapperMeta(false, new WrapperConfig(wrapperGroupSetup.getGroupName(),
                            wrapperGroupSetup.getHost(),
                            wrapperGroupSetup.getAutoUpdate())));

                    liptonMaster.getCommandManager().restart();

                    colouredConsoleProvider.info("Wrapper '" + wrapperGroupSetup.getGroupName() + "' was successfully created!");
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
    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider) {
        colouredConsoleProvider.info("create SERVERGROUP");
        colouredConsoleProvider.info("create WRAPPER");
    }
    //</editor-fold>
}
