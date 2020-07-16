package de.crycodes.de.spacebyter.commands;

import de.crycodes.de.spacebyter.LiptonWrapper;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.enums.RegisterType;
import de.crycodes.de.spacebyter.liptoncloud.meta.WrapperMeta;
import de.crycodes.de.spacebyter.liptoncloud.meta.config.WrapperConfig;
import de.crycodes.de.spacebyter.liptoncloud.packets.wrapper.out.UnRegisterPacket;

public class StopCommand extends CloudCommand {

    private final LiptonWrapper liptonWrapper;

    //<editor-fold desc="StopCommand">
    public StopCommand(String name, String description, String[] aliases, LiptonWrapper liptonWrapper) {
        super(name, description, aliases);
        this.liptonWrapper = liptonWrapper;
    }
    //</editor-fold>

    //<editor-fold desc="execute">
    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        liptonWrapper.setIsrunning(false);
        colouredConsoleProvider.info("Stopping Cloud");
        liptonWrapper.getWrapperMasterClient().sendPacket(new UnRegisterPacket(RegisterType.WRAPPER, new WrapperMeta(false, new WrapperConfig(liptonWrapper.getWrapperConfig().getWrapperID(), liptonWrapper.getWrapperConfig().getHost(), true))));
        System.exit(0);

        return false;
    }
    //</editor-fold>


}
