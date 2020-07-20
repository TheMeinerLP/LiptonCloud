package de.crycodes.modulecommand;

import de.crycodes.de.spacebyter.liptoncloud.addon.command.ModuleCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.ColouredConsoleProvider;

/**
 * Coded By CryCodes
 * Class: ModuleCommandExample
 * Date : 20.07.2020
 * Time : 11:59
 * Project: LiptonCloud
 */

public class ModuleCommandExample extends ModuleCommand {

    public ModuleCommandExample() {
        super("test", "This is an TestCommand!", "simple", "aliases");
    }

    public boolean execute(ColouredConsoleProvider colouredConsoleProvider, String command, String[] args) {
        //EXECUTE LOGIC
        return false;
    }
}
