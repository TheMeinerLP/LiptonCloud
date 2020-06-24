package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.config.ServerGroupConfig;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.command.CommandManager;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.GroupSetup;
import org.checkerframework.checker.units.qual.C;

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

                LiptonMaster.getInstance().getCommandManager().stop();

                GroupSetup setup = new GroupSetup();
                setup.start(LiptonMaster.getInstance().getCommandManager().getScanner());
                System.out.printf(setup.getServerName());

                LiptonMaster.getInstance().getCommandManager().restart();

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
