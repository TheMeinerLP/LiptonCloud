package de.crycodes.addon.multiproxy.utils;

import de.crycodes.de.spacebyter.network.channel.Identifier;
import de.crycodes.de.spacebyter.network.channel.NetworkChannel;
import de.crycodes.de.spacebyter.network.channel.Provider;

/**
 * Coded By CryCodes
 * Class: ProxyChannel
 * Date : 27.06.2020
 * Time : 15:03
 * Project: LiptonCloud
 */

public class ProxyChannel extends NetworkChannel {

    public ProxyChannel() {
        super(new Identifier("MULTIPROXY"), new Provider("CLOUD"));
    }

}
