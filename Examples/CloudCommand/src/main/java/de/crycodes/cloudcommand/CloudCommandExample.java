package de.crycodes.cloudcommand;

import de.crycodes.de.spacebyter.liptoncloud.command.CloudCommand;
import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

/**
 * Coded By CryCodes
 * Class: CloudCommandExample
 * Date : 20.07.2020
 * Time : 11:54
 * Project: LiptonCloud
 */

public class CloudCommandExample extends CloudCommand {

    public CloudCommandExample() {
        super("test", "This is an TestCommand!", "simple", "aliases");
    }

    protected boolean execute(CloudConsole colouredConsoleProvider, String command, String[] args) {
        //EXECUTE LOGIC
        return false;
    }
}
