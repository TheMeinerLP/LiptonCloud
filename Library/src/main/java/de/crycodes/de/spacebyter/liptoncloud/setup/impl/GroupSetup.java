package de.crycodes.de.spacebyter.liptoncloud.setup.impl;

import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

public class GroupSetup extends Setup {

    @SetupPart(id = 1, question = "What should the server group be called ?", forbiddenAnswers = {"lobby", "proxy"})
    private String serverName;

    public String getServerName() {
        return serverName;
    }

    @SetupPart(id = 2, question = "What is the maximum amount of ram the server should have?", forbiddenAnswers = {"0"})
    private int maxMem;

    public int getMaxMem() {
        return maxMem;
    }

    @SetupPart(id = 3, question = "What is the minimal amount of ram the server should have?", forbiddenAnswers = {"0"})
    private int minMem;

    public int getMinMem() {
        return minMem;
    }

    @SetupPart(id = 4, question = "Should the Server be Dynamic ? true | false", forbiddenAnswers = {""})
    private boolean dynamic;

    public boolean isDynamic() {
        return dynamic;
    }

    @SetupPart(id = 5, question = "How many Servers should allways be Online ?", forbiddenAnswers = {"0"})
    private int minServer;

    public int getMaxServer() {
        return minServer;
    }

    @SetupPart(id = 6, question = "How many Servers should be max Online ?", forbiddenAnswers = {"0"})
    private int maxyServer;

    public int getMinServer() {
        return maxyServer;
    }
}
