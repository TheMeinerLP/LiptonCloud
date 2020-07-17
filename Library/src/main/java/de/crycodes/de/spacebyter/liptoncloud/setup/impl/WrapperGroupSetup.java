package de.crycodes.de.spacebyter.liptoncloud.setup.impl;

import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

/**
 * Coded By CryCodes
 * Class: WrappperGroupSetup
 * Date : 26.06.2020
 * Time : 08:15
 * Project: LiptonCloud
 */

public class WrapperGroupSetup extends Setup {

    @SetupPart(id = 1, question = "What should the wrapper group be called ?", forbiddenAnswers = {"mainproxy"})
    private String groupName;

    @SetupPart(id = 2, question = "What should the wrapper's Host be ?", forbiddenAnswers = {})
    private String host;

    @SetupPart(id = 3, question = "Should the Wrapper use Auto-Update ?", forbiddenAnswers = {})
    private Boolean autoUpdate;

    public String getGroupName() {
        return groupName;
    }

    public String getHost() {
        return host;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }
}
