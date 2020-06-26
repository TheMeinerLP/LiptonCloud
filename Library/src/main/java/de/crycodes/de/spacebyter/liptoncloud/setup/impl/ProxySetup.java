package de.crycodes.de.spacebyter.liptoncloud.setup.impl;

import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

/**
 * Coded By CryCodes
 * Class: ProxySetup
 * Date : 26.06.2020
 * Time : 07:50
 * Project: LiptonCloud
 */

public class ProxySetup extends Setup {

    @SetupPart(id = 1, question = "What should the proxy group be called ?", forbiddenAnswers = {"lobby", "mainproxy"})
    private String groupName;

    @SetupPart(id = 2, question = "What should the proxy's ID be ?", forbiddenAnswers = {})
    private Integer id;

    @SetupPart(id = 3, question = "Should the Proxy group be the main ProxyGroup ?", forbiddenAnswers = {})
    private Boolean mainProxy;

    public String getGroupName() {
        return groupName;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getMainProxy() {
        return mainProxy;
    }
}
