package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.config.ServerGroupConfig;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;

public class ServiceCommand extends CloudCommand {

    public ServiceCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }


    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {


        return false;
    }

    private void sendUsage(ColouredConsoleProvider colouredConsoleProvider){
        colouredConsoleProvider.info("service stopserver name");
        colouredConsoleProvider.info("service stopgroup group");

    }

}
