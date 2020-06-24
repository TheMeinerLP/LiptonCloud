package de.crycodes.de.spacebyter.liptoncloud.setup.impl;

import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

public class GroupSetup extends Setup {

    @SetupPart(id = 1, question = "Please enter Groupname.", forbiddenAnswers = {"lobby", "proxy"})
    private String serverName;

    public String getServerName() {
        return serverName;
    }
}
