package de.crycodes.addon.signsystem.commands;

import de.crycodes.addon.signsystem.SignSystem;
import de.crycodes.addon.signsystem.setup.SignLayoutSetup;
import de.crycodes.de.spacebyter.LiptonMaster;
import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;
import de.crycodes.de.spacebyter.liptoncloud.meta.ServerGroupMeta;
import de.crycodes.de.spacebyter.liptoncloud.setup.impl.GroupSetup;

/**
 * Coded By CryCodes
 * Class: SignLayoutCreateCommand
 * Date : 25.06.2020
 * Time : 18:04
 * Project: LiptonCloud
 */

public class SignLayoutCreateCommand extends CloudCommand {

    private final SignSystem signSystem;

    public SignLayoutCreateCommand(String name, String description, String[] aliases, SignSystem signSystem) {
        super(name, description, aliases);
        this.signSystem = signSystem;
    }

    @Override
    protected boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {

        LiptonMaster.getInstance().getCommandManager().stop();

        SignLayoutSetup setup = new SignLayoutSetup();
        setup.start(LiptonMaster.getInstance().getCommandManager().getScanner());

        signSystem.getSignLayoutConfig().addSign(setup.getLayout());
        LiptonMaster.getInstance().getColouredConsoleProvider().info("Created Sing-Layout (" + setup.getLayout().getId() + ")");

        LiptonMaster.getInstance().getCommandManager().restart();

        return false;
    }
}
