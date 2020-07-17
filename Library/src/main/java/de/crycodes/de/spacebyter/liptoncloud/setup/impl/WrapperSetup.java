package de.crycodes.de.spacebyter.liptoncloud.setup.impl;

import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

/**
 * Coded By CryCodes
 * Class: WrapperSetup
 * Date : 17.07.2020
 * Time : 16:12
 * Project: LiptonCloud
 */

public class WrapperSetup extends Setup {

    @SetupPart(id = 1, question = "What should the wrapper group be called ?")
    private String groupName;

    @SetupPart(id = 2, question = "What is the master's host ?")
    private String masterhost;

    @SetupPart(id = 3, question = "Should the Wrapper use Coloured Console ?")
    private Boolean color;


    public String getGroupName() {
        return groupName;
    }

    public Boolean getColor() {
        return color;
    }

    public String getMasterhost() {
        return masterhost;
    }
}
