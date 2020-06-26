package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.ServerGroupConfig;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ProxyMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.GroupSetup;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.ProxySetup;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.WrapperGroupSetup;
import org.checkerframework.checker.units.qual.C;

import java.util.LinkedList;

public class CreateCommand extends CloudCommand {

    public CreateCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        switch(args.length) {
            default:
                sendUsage(colouredConsoleProvider);
                break;
            case 1:

                if(args[0].equalsIgnoreCase("servergroup")) {

                    LiptonMaster.getInstance().getCommandManager().stop();

                    GroupSetup groupSetup = new GroupSetup();
                    groupSetup.start(LiptonMaster.getInstance().getCommandManager().getScanner());

                   LiptonMaster.getInstance().getServerGroupConfig().create(new ServerGroupMeta(groupSetup.getServerName(),
                           groupSetup.getMaxMem(),
                           groupSetup.getMinMem(),
                           groupSetup.isDynamic(),
                           false ,
                           groupSetup.getMaxServer(),
                           groupSetup.getMinServer()));

                    LiptonMaster.getInstance().getCommandManager().restart();

                    colouredConsoleProvider.info("Servergroup '" + groupSetup.getServerName() + "' was successfully created!");
                    break;
                }
                if (args[0].equalsIgnoreCase("proxy")){

                    LiptonMaster.getInstance().getCommandManager().stop();

                    ProxySetup proxySetup = new ProxySetup();
                    proxySetup.start(LiptonMaster.getInstance().getCommandManager().getScanner());

                    LiptonMaster.getInstance().getProxyGroupConfig().create(new ProxyMeta(proxySetup.getGroupName(),
                            proxySetup.getId(),
                            proxySetup.getMainProxy()));

                    LiptonMaster.getInstance().getCommandManager().restart();

                    colouredConsoleProvider.info("Proxy '" + proxySetup.getGroupName() + "' was successfully created!");
                    break;
                }
                if (args[0].equalsIgnoreCase("wrapper")){

                    LiptonMaster.getInstance().getCommandManager().stop();

                    WrapperGroupSetup wrapperGroupSetup = new WrapperGroupSetup();
                    wrapperGroupSetup.start(LiptonMaster.getInstance().getCommandManager().getScanner());

                    LiptonMaster.getInstance().getWrapperConfig().create(new WrapperMeta(false, new WrapperConfig(wrapperGroupSetup.getGroupName(),
                            wrapperGroupSetup.getHost(),
                            wrapperGroupSetup.getAutoUpdate())));

                    LiptonMaster.getInstance().getCommandManager().restart();

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

    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider) {
        colouredConsoleProvider.info("create SERVERGROUP");
        colouredConsoleProvider.info("create TEMPLATE <Group>");
        colouredConsoleProvider.info("create PROXY");
        colouredConsoleProvider.info("create WRAPPER");
    }
}
