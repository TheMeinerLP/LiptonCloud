package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;

public class StopCommand extends CloudCommand {

    public StopCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        try {
            this.stopWrapper(colouredConsoleProvider);
        }catch(Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public void stopWrapper(ColouredConsoleProvider colouredConsoleProvider) throws InterruptedException {
        colouredConsoleProvider.info("Stopping Cloud");
        LiptonWrapper.getInstance().getWrapperMasterClient().sendPacket(new UnRegisterPacket(RegisterType.WRAPPER));
        System.exit(0);
    }

}
